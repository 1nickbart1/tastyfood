package by.tastyfood.dao.i;

import by.tastyfood.dao.IDao;
import by.tastyfood.dao.entity.Recipe;
import by.tastyfood.dao.entity.RecipiesCategory;
import by.tastyfood.dao.entity.RecipiesProduct;
import by.tastyfood.dao.entity.SliderImages;

import java.util.List;

/**
 * Created by Nikolay on 03.06.2017.
 */
public interface IRecipeDao extends IDao<Recipe, Long> {

    public List<Recipe> getAllRecepiesForCategory(RecipiesCategory recCategory);
    public List<Recipe> getAllRecepiesForCategory(Long recCategoryId);
    public List<RecipiesProduct> getAllProductsForRecipe(Long recipeId);
    public List<SliderImages> getImagesForSlider(Long sliderId);
}
