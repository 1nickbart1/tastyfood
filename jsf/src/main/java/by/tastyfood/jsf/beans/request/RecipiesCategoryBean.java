package by.tastyfood.jsf.beans.request;

import by.tastyfood.dao.entity.RecipiesCategory;
import by.tastyfood.model.Recipies.RecipiesCategoryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Nikolay on 03.06.2017.
 */
@Component
@ManagedBean(name = "recipiesCategoryBean")
@RequestScoped
@Scope("request")
public class RecipiesCategoryBean {

    @Autowired
    public RecipiesCategoryLoader recipiesCategoryLoader;

    private List<RecipiesCategory> recipiesCategories;

    @PostConstruct
    public void init(){
        System.out.println("start init in RecipiesCategoryBean");
        recipiesCategories = recipiesCategoryLoader.getRecipiesCategoryWithRecipies();
    }

    public List<RecipiesCategory> getRecipiesCategories() {

        return recipiesCategories;
    }



    public void setRecipiesCategories(List<RecipiesCategory> recipiesCategories) {
        this.recipiesCategories = recipiesCategories;
    }
}
