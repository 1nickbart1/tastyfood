package by.tastyfood.jsf.beans.view;

import by.tastyfood.dao.entity.*;
import by.tastyfood.enums.ParamNamesEnum;
import by.tastyfood.jsf.beans.auth.AuthBean;
import by.tastyfood.model.Recipies.RecipiesLoader;
import by.tastyfood.model.Recipies.RecipiesModel;
import by.tastyfood.model.comments.CommentsService;
import by.tastyfood.model.security.UserService;
import by.tastyfood.recepies.RecipeEnergyValue;
import by.tastyfood.utils.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Component
@ManagedBean(name = "recipeBean")
@ViewScoped
@Scope("view")
public class RecipeBean {

    private final String CAT_ID_NAME = "recipeId";

    @Autowired
    private RecipiesLoader recipiesLoader;
    @Autowired
    AuthBean authBean;
    @Autowired
    UserService userService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    private RecipiesModel recipiesModel;

    //    private Integer rating;
    private Recipe recipe;
    private Long recipeId;
    private List<SliderImages> sliderImages;
    private RecipeEnergyValue recipeEnergyValue;

    private String commentText;
    private RecipeRating recipeRating;
    private boolean isInFavorite;
    private Users curUser;
//    private List<RecipiesProduct> products;


    @PostConstruct
    public void init() {

        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();

        recipeId = LongUtils.getLongFromString(params.get(ParamNamesEnum.RECIPE_ID.getParam()));
        recipe = recipiesLoader.getRecipeById(recipeId);

        if (recipe == null) {

            //ToDo redirect to error page
        }

        Long sliderId = recipe.getSliderId();
        if (sliderId == null) {
            sliderId = recipeId;
        }


        sliderImages = recipiesLoader.getImagesForSlider(sliderId);
        recipeEnergyValue = recipiesModel.getRecipeEnergyValueFromProducts(recipe.getRecipiesProductSet());
        recipeRating = recipiesModel.getRecipeRatingForUser(recipe, authBean.getCurrentUserName());


        isInFavorite = recipiesModel.findFavoriteRecipeForUser(recipe, getCurUser()) != null;

//        products = recipiesLoader.getProductsForRecipe(recipeId);

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<SliderImages> getSliderImages() {
        return sliderImages;
    }

    public void setSliderImages(List<SliderImages> sliderImages) {
        this.sliderImages = sliderImages;
    }

    public RecipeEnergyValue getRecipeEnergyValue() {
        return recipeEnergyValue;
    }

    public void setRecipeEnergyValue(RecipeEnergyValue recipeEnergyValue) {
        this.recipeEnergyValue = recipeEnergyValue;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public boolean isInFavorite() {
        return isInFavorite;
    }

    public void setInFavorite(boolean inFavorite) {
        isInFavorite = inFavorite;
    }
    //    public Integer getRating() {
//        return rating;
//    }
//
//    public void setRating(Integer rating) {
//        this.rating = rating;
//    }

    public RecipeRating getRecipeRating() {
        return recipeRating;
    }

    public void setRecipeRating(RecipeRating recipeRating) {
        this.recipeRating = recipeRating;
    }

    public void saveComment(AjaxBehaviorEvent event) {

        Comments newComment = new Comments();
        newComment.setDeleted(0);
        newComment.setRecipe(recipe);

        String userName = authBean.getCurrentUserName();
        System.out.println("qqqqqqqqqqqq"+userName);
        if ("".equals(userName)) {
            userName = authBean.getANONYMOUS_USER();
        }

//        Users curUser = userService.findByUsername(userName);
        newComment.setUser(getCurUser());
        newComment.setText(commentText);
        newComment.setDate(new Date());
        newComment.setDate(new Date());
        Long id = commentsService.saveComment(newComment);
        newComment.setId(id);
        recipe.getComments().add(newComment);
    }

    public void rateListener(AjaxBehaviorEvent event) {
        recipiesModel.saveRating(recipeRating);

    }

    public void favoriteListener(AjaxBehaviorEvent event) {
      if(getCurUser() == null){
           System.out.println("user == null");
           //ToDo show faces message
           return;
       }
        System.out.println("saveFavRecipe");
       recipiesModel.saveOrUpDateFavoriteRecipe(recipe, getCurUser());

       isInFavorite = !isInFavorite;

    }

    private Users getCurUser() {
        if (curUser == null) {
            curUser = userService.findByUsername(authBean.getCurrentUserName());
        }

        System.out.println("getCurUser ="+curUser);
        return curUser;
    }


}
