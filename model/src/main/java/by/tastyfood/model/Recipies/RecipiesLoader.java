package by.tastyfood.model.Recipies;

import by.tastyfood.dao.entity.Recipe;
import by.tastyfood.dao.entity.RecipiesProduct;
import by.tastyfood.dao.entity.SliderImages;
import by.tastyfood.dao.i.IRecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Service
public class RecipiesLoader {

    @Autowired
    private IRecipeDao recipeDao;

    public List<Recipe> getRecipiesForCategory(Long catId){

        return recipeDao.getAllRecepiesForCategory(catId);
    }

    public List<Recipe> getAllRecipies(){

        return recipeDao.getAll();
    }

    public Recipe getRecipeById(Long id){
       Recipe  recipe= recipeDao.get(id);

              return recipe;

    }

    public List<RecipiesProduct> getProductsForRecipe(Long id){
        List<RecipiesProduct> products = recipeDao.getAllProductsForRecipe(id);

        return products;
    }

    public List<SliderImages> getImagesForSlider(Long sliderId){
        List<SliderImages> images = recipeDao.getImagesForSlider(sliderId);

        return images;
    }
}


