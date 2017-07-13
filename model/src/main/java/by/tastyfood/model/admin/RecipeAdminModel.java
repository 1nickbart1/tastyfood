package by.tastyfood.model.admin;

import by.tastyfood.dao.entity.*;
import by.tastyfood.dao.i.IProductDao;
import by.tastyfood.dao.i.IRecipeDao;
import by.tastyfood.dao.i.IRecipeProductDao;
import by.tastyfood.dao.i.ISliderImagesDao;
import by.tastyfood.dao.impl.SliderImagesDao;
import by.tastyfood.dao.pk.RecipiesProductPK;
import by.tastyfood.model.Recipies.RecipiesCategoryLoader;
import by.tastyfood.model.Recipies.RecipiesLoader;
import by.tastyfood.model.image.ImageService;
import by.tastyfood.recepies.RecipeImgDto;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nikolay on 12.06.2017.
 */
@Service
public class RecipeAdminModel {
    @Autowired
    private RecipiesCategoryLoader recipiesCategoryLoader;
    @Autowired
    IProductDao productDao;
    @Autowired
    ISliderImagesDao sliderImagesDao;
    @Autowired
    ImageService imageService;
    @Autowired
    IRecipeDao recipeDao;
    @Autowired
    IRecipeProductDao recipeProductDao;
    @Autowired
    RecipiesLoader recipiesLoader;

    public List<RecipiesCategory> getAllRecipiesCategories() {
        return recipiesCategoryLoader.getAllRecipiesCategory();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<String> getProductNames(List<Product> products) {
        List<String> destList = new ArrayList<>();
        products.forEach(product -> destList.add(product.getName()));
        return destList;

    }

    public List<Recipe> getAllRecipies(){
        return recipeDao.getAll();
    }

    public Product findProductInListByname(String name, List<Product> products) {

        for (Product product : products) {

            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;
    }

    public Map<String, ImageEntity> createImageEntitiesFromByteMapAndRecipe(Map<String, byte[]> imagesByteMap, Recipe recipe) {
        Map<String, ImageEntity> destMap = new HashMap<>();
        for (String key : imagesByteMap.keySet()) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(key);
            imageEntity.setImage(imagesByteMap.get(key));
            imageEntity.setDescription(recipe.getTitle());
            destMap.put(key, imageEntity);
        }
        return destMap;

    }


    public void saveRecipe(RecipeImgDto recipeImgDto) {
        Map<String, ImageEntity> destSliderImageMap =
                createImageEntitiesFromByteMapAndRecipe(recipeImgDto.getImageBytemap(), recipeImgDto.getRecipe());
        Recipe recipe = recipeImgDto.getRecipe();
        ImageEntity titleImage = destSliderImageMap.get(recipeImgDto.getTitleImagename());
        destSliderImageMap.remove(recipeImgDto.getTitleImagename());


        Long titleImageId = imageService.saveImg(titleImage);
        recipe.setImageId(titleImageId);

        Long recipeId = recipeDao.add(recipe);
        recipe.setSliderId(recipeId);
        recipe.setId(recipeId);
        recipeDao.update(recipe);

        saveSliderImages(destSliderImageMap.values(), recipe);
        saveRecipiesProduct(recipe);

    }

    public void updateRecipe(RecipeImgDto recipeImgDto) {
        Recipe recipe = recipeImgDto.getRecipe();

        if (recipe.getId() == null) {
            throw new RuntimeException("cant edit! id = null");
        }



        Map<String, ImageEntity> destSliderImageMap =
                createImageEntitiesFromByteMapAndRecipe(recipeImgDto.getImageBytemap(), recipeImgDto.getRecipe());

        ImageEntity titleImage = destSliderImageMap.get(recipeImgDto.getTitleImagename());
        destSliderImageMap.remove(recipeImgDto.getTitleImagename());
        List<ImageEntity> oldImages = imageService.getSliderImages(recipe.getSliderId());
//        oldImages.add(imageService.getImageById(recipe.getImageId()));

        Long titleImageId = imageService.saveOrUpdateImage(titleImage, oldImages);
        titleImage.setId(titleImageId);
        deleteIfExistsliderImage(titleImage, recipe.getSliderId());

        if(titleImageId != recipe.getImageId()){
            imageService.deleteImage(recipe.getImageId());
        }
        recipe.setImageId(titleImageId);

        recipeDao.update(recipe);


        List<RecipiesProduct> oldProducts = recipiesLoader.getProductsForRecipe(recipe.getId());
        oldProducts.forEach(product -> recipeProductDao.delete(product));
        saveRecipiesProduct(recipe);

        //тут уже будут удалены все неиспользуемые слайдеры
        imageService.deleteUnusedImages(oldImages, recipeImgDto);

        List<ImageEntity> imagesForUpdate = oldImages.stream().filter(
                oldImage -> destSliderImageMap.keySet().contains(oldImage.getName())
        ).collect(Collectors.toList());
        updateSliderImages(imagesForUpdate, recipe);


        Collection<ImageEntity> imagesForSave = destSliderImageMap.values();
        imagesForSave = imagesForSave.stream().filter(image ->
                !imagesForUpdate.contains(image)).collect(Collectors.toList());
        saveSliderImages(imagesForSave, recipe);

    }

    public void updateSliderImages(Collection<ImageEntity> imageEntities, Recipe recipe) {
        for (ImageEntity image : imageEntities) {

            SliderImages sliderForUpdate = image.getSliderImages().stream().
                    filter(slider -> slider.getSliderId() == recipe.getSliderId()).findFirst().orElse(null);
            if(sliderForUpdate == null){
                continue;
            }

            sliderImagesDao.delete(sliderForUpdate);
            sliderForUpdate.setImage(image);
            sliderForUpdate.setSliderId(recipe.getId());

            sliderImagesDao.add(sliderForUpdate);
        }
    }


    private void saveSliderImages(Collection<ImageEntity> imageEntities, Recipe recipe) {
        for (ImageEntity image : imageEntities) {
            Long imgId = imageService.saveImg(image);



            image.setId(imgId);

            SliderImages sliderImages = new SliderImages();
            sliderImages.setImage(image);
            sliderImages.setSliderId(recipe.getId());

            sliderImagesDao.add(sliderImages);
        }
    }

    private void saveRecipiesProduct(Recipe recipe) {
        for (RecipiesProduct recipiesProduct : recipe.getRecipiesProductSet()) {

            recipiesProduct.setRecipe(recipe);
            RecipiesProductPK pk = new RecipiesProductPK();
            pk.setProductId(recipiesProduct.getProduct().getId());
            pk.setRecipeId(recipe.getId());
            recipiesProduct.setPk(pk);
            recipeProductDao.add(recipiesProduct);
        }
    }



    public RecipeImgDto getRecipeImgDto(Long recipeId) {
        Recipe recipe = recipeDao.get(recipeId);

        List<ImageEntity> sliderImages = imageService.getSliderImages(recipe.getSliderId());

        Map<String, byte[]> imageByteMap = new HashMap<>();

        for (ImageEntity image : sliderImages) {
            imageByteMap.put(image.getName(), image.getImage());

        }

        ImageEntity titleImage = imageService.getImageById(recipe.getImageId());

        imageByteMap.put(titleImage.getName(), titleImage.getImage());

        RecipeImgDto recipeImgDto = new RecipeImgDto();
        recipeImgDto.setImageBytemap(imageByteMap);
        recipeImgDto.setRecipe(recipe);
        recipeImgDto.setTitleImagename(titleImage.getName());

        return recipeImgDto;

    }
    public void deleteIfExistsliderImage(ImageEntity image, Long sliderId){
       sliderImagesDao.delete(image, sliderId);
    }


}
