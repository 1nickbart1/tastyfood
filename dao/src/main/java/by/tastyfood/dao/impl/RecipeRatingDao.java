package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.RecipeRating;
import by.tastyfood.dao.i.IRecipeRatingDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 23.06.2017.
 */
@Service
public class RecipeRatingDao extends AbstractDaoImpl<RecipeRating, Long> implements IRecipeRatingDao {

    public RecipeRatingDao(){
        super(RecipeRating.class);
    }
}
