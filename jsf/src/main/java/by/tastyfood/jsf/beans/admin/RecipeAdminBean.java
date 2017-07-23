package by.tastyfood.jsf.beans.admin;

import by.tastyfood.dao.entity.*;
import by.tastyfood.jsf.beans.auth.AuthBean;
import by.tastyfood.jsf.beans.session.SessionImageContainer;
import by.tastyfood.jsf.utils.JsfUtils;
import by.tastyfood.model.admin.RecipeAdminModel;
import by.tastyfood.model.image.ImageService;
import by.tastyfood.model.security.UserService;
import by.tastyfood.recepies.RecipeImgDto;
import by.tastyfood.utils.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Nikolay on 10.06.2017.
 */
@Component
@ManagedBean(name = "recipeAdminBean")
@Scope("view")
public class RecipeAdminBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    AuthBean authBean;
    @Autowired
    SessionImageContainer imageContainer;

    @Autowired
    UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private RecipeAdminModel adminModel;

    private Recipe currRecipe;
    private boolean editMode;
    private Map<String, byte[]> sliderImagesBytesMap;
    private RecipiesCategory recipiesCategory;
    private List<RecipiesCategory> avaliableCategories;
    private List<Product> avaliableProducts;
    //    private Set<RecipiesProduct> recipiesProductSet;
    private String currProductName;
    //    private String recipeText;
    private String mainImageKey;
    private String tempImgId;
    private Double units;
    private String recipeCategoryId;


    @PostConstruct
    public void init() {
        System.out.println("start init RecipeAdminBean");
//        sliderImagesBytesMap = new HashMap<>();
        sliderImagesBytesMap = imageContainer.getSliderImagesBytesMap();
        editMode = true;
        currRecipe = new Recipe();
        currRecipe.setRecipiesCategory(new RecipiesCategory());
        currRecipe.setRecipiesProductSet(new HashSet<>());
        avaliableCategories = adminModel.getAllRecipiesCategories();
        avaliableProducts = adminModel.getAllProducts();
//        recipiesProductSet = new HashSet<>();
//        recipeText="";
        currRecipe.setText("");
        Users user = userService.findByUsername(authBean.getCurrentUserName()) ;
        currRecipe.setAuthor(user);
    }

    public void init(RecipeImgDto recipeImgDto) {

        avaliableCategories = adminModel.getAllRecipiesCategories();
        avaliableProducts = adminModel.getAllProducts();
        currRecipe = recipeImgDto.getRecipe();
        recipeCategoryId = currRecipe.getRecipiesCategory().getId().toString();
        sliderImagesBytesMap = recipeImgDto.getImageBytemap();
        imageContainer.setSliderImagesBytesMap(sliderImagesBytesMap);
        mainImageKey = recipeImgDto.getTitleImagename();

    }

    public Recipe getCurrRecipe() {
        return currRecipe;
    }

    public void setCurrRecipe(Recipe currRecipe) {
        this.currRecipe = currRecipe;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }


    public void saveSlidersImg(FileUploadEvent event) {
        System.out.println("start saveSlidersImg");
        UploadedFile file = event.getFile();
        System.out.println(StringUtils.transliterate(file.getFileName()));
        System.out.println(file.getContents().length);

        sliderImagesBytesMap.put(StringUtils.transliterate(file.getFileName()), file.getContents());

    }

//    public StreamedContent getStreamedTitleImage() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
//        String imageKey = (String) myRequest.getParameter("image_key");
//
//        byte[] imgBytes = sliderImagesBytesMap.get(imageKey);
//
//        if (imgBytes == null) {
//            return new DefaultStreamedContent();
//        }
//        DefaultStreamedContent streamImage = null;
//
//        try {
//            System.out.println("byte array size =" + imgBytes.length);
//
//            streamImage = new DefaultStreamedContent(new ByteArrayInputStream(imgBytes));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("stream created");
//
//        return streamImage;
//
//    }


    public void deleteImg(AjaxBehaviorEvent event) {
        System.out.println("start delete");
        Object imagekeyParam = event.getComponent().getAttributes().get("image_key");
        String imagekey = null;
        if (imagekeyParam == null) {
            System.out.println("exit from delete");
            return;
        }

        imagekey = imagekeyParam.toString();

        sliderImagesBytesMap.remove(imagekey);
    }

    public void addProduct(AjaxBehaviorEvent event) {
        Product product = adminModel.findProductInListByname(currProductName, avaliableProducts);

        RecipiesProduct recipiesProduct = new RecipiesProduct();
        recipiesProduct.setRecipe(currRecipe);
        recipiesProduct.setProduct(product);
        recipiesProduct.setUnits(units);

        if (currRecipe.getRecipiesProductSet().contains(recipiesProduct)) {
            currRecipe.getRecipiesProductSet().remove(recipiesProduct);
        }
        currRecipe.getRecipiesProductSet().add(recipiesProduct);
        currProductName = null;
        units = null;
    }

    public void deleteProduct(AjaxBehaviorEvent event) {
        Object productAttr = event.getComponent().getAttributes().get("recipeProduct");
        if (productAttr == null) {
            return;
        }

        RecipiesProduct recipiesProduct = (RecipiesProduct) productAttr;

        currRecipe.getRecipiesProductSet().remove(recipiesProduct);


    }


    public String getTempUrl() {
        return tempImgId;
    }

    public void setTempUrl(String tempUrl) {
        this.tempImgId = tempUrl;
    }

    public void addImageToText() {
        StringBuilder newText = new StringBuilder();
        newText.append(currRecipe.getText());
//        newText.append(recipeText);
        newText.append("<img src='/api/image?id=");
        newText.append(tempImgId);
        newText.append("'>");
        currRecipe.setText(newText.toString());


    }

    public void clearRecipeText(AjaxBehaviorEvent event) {
//        recipeText ="";
        currRecipe.setText("");
    }

    public void saveRecipe(ActionEvent event) {
        System.out.println("start saving");
        RecipeImgDto recipeImgDto = new RecipeImgDto(currRecipe, sliderImagesBytesMap, mainImageKey);

        List<String> err = recipeImgDto.findErrors();

        if (err.size() > 0) {
            err.forEach(errText -> JsfUtils.showMsg("Error", errText));
            return;
        }




        if (currRecipe.getId() != null) {
            adminModel.updateRecipe(recipeImgDto);
        } else {
            adminModel.saveRecipe(recipeImgDto);
        }

        init();

    }

    public void loadImageToRecipeText(FileUploadEvent event) {
        byte[] fileBytes = event.getFile().getContents();
        String name = event.getFile().getFileName();
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImage(fileBytes);
        imageEntity.setName(name);
        imageEntity.setDescription(currRecipe.getTitle());

        Long imageId = imageService.saveImg(imageEntity);

        StringBuilder newText = new StringBuilder();
//        newText.append(recipeText);
//        newText.append("<img src='/api/image?id=");
//        newText.append(imageId);
//        newText.append("'>");
//        recipeText = newText.toString();
//

        tempImgId = imageId.toString();
        System.out.println("new tempImgId = " + tempImgId);
    }


    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public Set<String> getKeysForSlidersMap() {
        return sliderImagesBytesMap.keySet();
    }

    public Map<String, byte[]> getSliderImagesBytesMap() {
        return sliderImagesBytesMap;
    }

    public void setSliderImagesBytesMap(Map<String, byte[]> sliderImagesBytesMap) {
        this.sliderImagesBytesMap = sliderImagesBytesMap;
    }


    public String getMainImageKey() {
        return mainImageKey;
    }

    public void setMainImageKey(String mainImageKey) {

        System.out.println("ssssssssss" + mainImageKey);
        this.mainImageKey = mainImageKey;
    }

    public RecipiesCategory getRecipiesCategory() {
        return recipiesCategory;
    }

    public void setRecipiesCategory(RecipiesCategory recipiesCategory) {
        this.recipiesCategory = recipiesCategory;
    }

    public List<RecipiesCategory> getAvaliableCategories() {
        return avaliableCategories;
    }

    public void setAvaliableCategories(List<RecipiesCategory> avaliableCategories) {
        this.avaliableCategories = avaliableCategories;
    }

    public List<Product> getAvaliableProducts() {
        return avaliableProducts;
    }

    public void setAvaliableProducts(List<Product> avaliableProducts) {
        this.avaliableProducts = avaliableProducts;
    }

//    public Set<RecipiesProduct> getRecipiesProductSet() {
//        return recipiesProductSet;
//    }
//
//    public void setRecipiesProductSet(Set<RecipiesProduct> recipiesProductSet) {
//        this.recipiesProductSet = recipiesProductSet;
//    }

    public List<String> getProductsNames() {
        return adminModel.getProductNames(avaliableProducts);
    }

    public String getCurrProductName() {
        return currProductName;
    }

    public void setCurrProductName(String currProductName) {
        this.currProductName = currProductName;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

//    public String getRecipeText() {
//        System.out.println("getRecipeText :::"+recipeText);
//        return recipeText;
//    }
//
//    public void setRecipeText(String recipeText) {
//        System.out.println("setRecipeText :::"+recipeText);
//        this.recipeText = recipeText;
//    }


    public String getRecipeCategoryId() {
        return recipeCategoryId;
    }

    public void setRecipeCategoryId(String recipeCategoryId) {

        for (RecipiesCategory category : avaliableCategories) {

            if (category.getId().toString().equals(recipeCategoryId)) {
                currRecipe.setRecipiesCategory(category);
            }
        }

        this.recipeCategoryId = recipeCategoryId;
    }
}
