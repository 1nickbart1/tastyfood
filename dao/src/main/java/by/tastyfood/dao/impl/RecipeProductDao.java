package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.RecipiesProduct;
import by.tastyfood.dao.i.IRecipeProductDao;
import by.tastyfood.dao.pk.RecipiesProductPK;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay on 13.06.2017.
 */
@Service
public class RecipeProductDao extends AbstractDaoImpl<RecipiesProduct, RecipiesProductPK> implements IRecipeProductDao {
   public RecipeProductDao(){
       super(RecipiesProduct.class);
   }


}
