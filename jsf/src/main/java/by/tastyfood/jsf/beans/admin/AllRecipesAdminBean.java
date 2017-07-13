package by.tastyfood.jsf.beans.admin;

import by.tastyfood.dao.entity.Product;
import by.tastyfood.dao.entity.Recipe;
import by.tastyfood.enums.CompositePageNameEnum;
import by.tastyfood.model.admin.RecipeAdminModel;
import by.tastyfood.recepies.RecipeImgDto;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

/**
 * Created by Nikolay on 16.06.2017.
 */
@Component
@ManagedBean(name = "allRecipesAdminBean")
@ViewScoped
//@PropertySource(value =  "classpath:messages.properties")
@Scope("request")
public class AllRecipesAdminBean {

    @Autowired
    private RecipeAdminModel adminModel;
    @Autowired
    private AdminController adminController;
    @Autowired
    private RecipeAdminBean recipeAdminBean;
    private List<Recipe> allRecipies;

    @PostConstruct
    public void init(){
        allRecipies = adminModel.getAllRecipies();
    }


    public List<Recipe> getAllRecipies() {
        return allRecipies;
    }

    public void setAllRecipies(List<Recipe> allRecipies) {
        this.allRecipies = allRecipies;
    }

    public void editRecipeListener(AjaxBehaviorEvent event){
        System.out.println("start edit recipe");
        Object recipeFromEvent = event.getComponent().getAttributes().get("recipe");
        Recipe recipe = null;
      if(recipeFromEvent != null){
            recipe = (Recipe)recipeFromEvent;
        }

        if(recipe == null){
            System.out.println("recipe = null");
            return;
        }
      System.out.println("id = "+ recipe.getId());


        RecipeImgDto recipeImgDto =  adminModel.getRecipeImgDto(recipe.getId());
        recipeAdminBean.init(recipeImgDto);
        adminController.updateCompositepage(CompositePageNameEnum.RECIPE);
        System.out.println("page name = "+adminController.getCurAdminPage().getName());
   }
}
