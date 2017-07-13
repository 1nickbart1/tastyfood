package by.tastyfood.model.Recipies;

import by.tastyfood.dao.entity.Product;
import by.tastyfood.dao.entity.RecipiesCategory;
import by.tastyfood.dao.i.IProductDao;
import by.tastyfood.dao.i.IRecipiesCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nikolay on 03.06.2017.
 */
@Service
public class RecipiesCategoryLoader {

    @Autowired
    private IRecipiesCategoryDao recipiesCategoryDao;

    @Autowired
    private IProductDao productDao;


    public List<RecipiesCategory> getAllRecipiesCategory(){

        return recipiesCategoryDao.getAll();
    }

    public List<RecipiesCategory> getRecipiesCategoryWithRecipies(){
        List<RecipiesCategory> recipiesCategory = recipiesCategoryDao.getAllRecipiesCatWithRecipies();
        recipiesCategory.forEach(rec-> System.out.println(rec.getName()));
        if (recipiesCategory == null){

            recipiesCategory = Collections.emptyList();
        }

        return recipiesCategory;

    }


}
