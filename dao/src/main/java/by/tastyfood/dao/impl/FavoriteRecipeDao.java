package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.FavoriteRecipe;
import by.tastyfood.dao.i.IFavoriteRecipeDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 26.06.2017.
 */
@Service
public class FavoriteRecipeDao extends AbstractDaoImpl<FavoriteRecipe, Long>  implements IFavoriteRecipeDao{
    public FavoriteRecipeDao(){
        super(FavoriteRecipe.class);
    }
}
