package by.tastyfood.jsf.beans.view;

import by.tastyfood.dao.entity.Recipe;
import by.tastyfood.dao.entity.RecipiesCategory;
import by.tastyfood.enums.ParamNamesEnum;
import by.tastyfood.jsf.beans.auth.AuthBean;
import by.tastyfood.model.Recipies.RecipiesLoader;
import by.tastyfood.model.Recipies.RecipiesModel;
import by.tastyfood.utils.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Component
@ManagedBean(name = "recipiesBean")
@ViewScoped
@Scope("view")
public class RecipiesBean {

//    private final String CAT_ID_NAME = "catId";

    @Autowired
    RecipiesLoader recipiesLoader;
    @Autowired
    RecipiesModel recipiesModel;
    @Autowired
    AuthBean authBean;

    private RecipiesCategory currentCategory;
    private  Long recCategoryId;
    private  List<Recipe> recipeList;

    @PostConstruct
    public void init() {

        System.out.println("start init in RecipiesBean");

        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();

        recCategoryId = LongUtils.getLongFromString(params.get(ParamNamesEnum.CATEGORY_ID.getParam()));
        String favoriteFlag = params.get(ParamNamesEnum.FAVORITE.getParam());



        if (recCategoryId != null) {
            recipeList = recipiesLoader.getRecipiesForCategory(recCategoryId);

        } else if(favoriteFlag != null){
            System.out.println("start favorite");
            recipeList = recipiesModel.getFavoriteRecipies(authBean.getCurrentUserName());

        }else{
            System.out.println("start else");
            recipeList = recipiesLoader.getAllRecipies();
        }


    }





    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public RecipiesCategory getCurrentCategory() {

        if(recipeList.size() > 0){
            currentCategory = recipeList.get(0).getRecipiesCategory();
        }

        return currentCategory;
    }

    public void setCurrentCategory(RecipiesCategory currentCategory) {
        this.currentCategory = currentCategory;
    }

//    public Integer calcRating(Recipe recipe){
//        return recipiesModel.calculateRecipeRating(recipe);
//    }
}
