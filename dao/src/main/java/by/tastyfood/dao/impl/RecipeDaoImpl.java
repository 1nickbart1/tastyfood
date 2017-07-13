package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.Recipe;
import by.tastyfood.dao.entity.RecipiesCategory;
import by.tastyfood.dao.entity.RecipiesProduct;
import by.tastyfood.dao.entity.SliderImages;
import by.tastyfood.dao.i.IRecipeDao;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay on 03.06.2017.
 */
@Service
public class RecipeDaoImpl extends AbstractDaoImpl<Recipe, Long> implements IRecipeDao {

    public RecipeDaoImpl(){
        super(Recipe.class);
    }

    public List<Recipe> getAllRecepiesForCategory(RecipiesCategory recCategory) {
        @SuppressWarnings("JpaQlInspection")
        Query query =getSession().createQuery("from Recipe  recipe where recipe.recipiesCategory = :recCategory")
                .setLong("recCategory", recCategory.getId());
        List<Recipe> recipies = (List<Recipe>) query.list();

        return recipies;

    }

    public List<Recipe> getAllRecepiesForCategory(Long recCategoryId) {
        if(recCategoryId == null){
            return null;
        }

        RecipiesCategory recCat = new RecipiesCategory();
        recCat.setId(recCategoryId);

        return getAllRecepiesForCategory(recCat);

    }

    public List<RecipiesProduct> getAllProductsForRecipe(Long recipeId) {
        Query query =getSession().createQuery("from RecipiesProduct  recProd where recProd.recipe = :recipeId")
                .setLong("recipeId", recipeId);
        List<RecipiesProduct> products = (List<RecipiesProduct>) query.list();

        return products;
    }

    public List<SliderImages> getImagesForSlider(Long sliderId) {
        Query query =getSession().createQuery("from SliderImages  sliderImage where sliderImage.sliderId = :sliderId")
                .setLong("sliderId", sliderId);
        List<SliderImages> images = (List<SliderImages>) query.list();

        return images;
    }
}
