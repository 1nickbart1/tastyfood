package by.tastyfood.jsf.beans.admin;

import by.tastyfood.enums.CompositePageNameEnum;
import by.tastyfood.jsf.utils.JsfUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;

/**
 * Created by Nikolay on 10.06.2017.
 */
@Component
@ManagedBean(name = "adminController")
@ViewScoped
@Scope("view")
public class AdminController implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String ADMIN_URL = "admin.";

    private CompositePageNameEnum curAdminPage;
    private boolean showAdminNavigation ;


    @PostConstruct
    public void init(){
        System.out.println("AdminController init ");
        curAdminPage = CompositePageNameEnum.PRODUCT;
        showAdminNavigation = true;
//        showAdminNavigation = JsfUtils.isCurUrlContainString(ADMIN_URL);

    }




    public CompositePageNameEnum getCurAdminPage() {
        return curAdminPage;
    }

    public void setCurAdminPage(CompositePageNameEnum curAdminPage) {
        this.curAdminPage = curAdminPage;
    }

    public void productEditListener(AjaxBehaviorEvent actionEvent) {
        updateCompositepage( CompositePageNameEnum.PRODUCT);
    }
    public void  recipeEditListener(AjaxBehaviorEvent actionEvent){
        updateCompositepage( CompositePageNameEnum.RECIPE);

    }
    public void  allRecipeEditListener(AjaxBehaviorEvent actionEvent){
        updateCompositepage( CompositePageNameEnum.ALL_RECIPIES);

    }

    public void  updateCompositepage(CompositePageNameEnum curPage ){
        curAdminPage = curPage;

    }




    public boolean isShowAdminNavigation() {
        return showAdminNavigation;
    }

    public void setShowAdminNavigation(boolean showAdminNavigation) {
        this.showAdminNavigation = showAdminNavigation;
    }


}
