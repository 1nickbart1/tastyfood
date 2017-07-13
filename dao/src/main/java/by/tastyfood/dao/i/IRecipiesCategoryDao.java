package by.tastyfood.dao.i;

import by.tastyfood.dao.IDao;
import by.tastyfood.dao.entity.RecipiesCategory;

import java.util.List;

/**
 * Created by Nikolay on 03.06.2017.
 */
public interface IRecipiesCategoryDao extends IDao<RecipiesCategory,Long> {

    public List<RecipiesCategory> getAllRecipiesCatWithRecipies();
}
