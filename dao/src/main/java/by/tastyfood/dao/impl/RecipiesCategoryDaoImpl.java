package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.RecipiesCategory;
import by.tastyfood.dao.i.IRecipiesCategoryDao;
import org.hibernate.Query;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by Nikolay on 03.06.2017.
 */
@Service
public class RecipiesCategoryDaoImpl extends AbstractDaoImpl<RecipiesCategory, Long> implements IRecipiesCategoryDao {

    public RecipiesCategoryDaoImpl(){
        super(RecipiesCategory.class);
    }


    public List<RecipiesCategory> getAllRecipiesCatWithRecipies() {
        Query query =getSession().createQuery("from RecipiesCategory  recCat where recCat.recipeSet IS NOT EMPTY");
        List<RecipiesCategory> recipiesCatWithReRecipies = (List<RecipiesCategory>) query.list();

        return recipiesCatWithReRecipies;
    }
}
