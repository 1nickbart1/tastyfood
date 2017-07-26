package by.tastyfood.model.Recipies;

import by.tastyfood.dao.entity.Recipe;
import by.tastyfood.recepies.RecipeEnergyValue;
import by.tastyfood.recepies.response.RecipeProductInfoResponse;
import by.tastyfood.utils.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 25.07.2017.
 */
@Service
public class RecipeControllerHandler {
    @Autowired
    RecipiesModel recipiesModel;
    @Autowired
    RecipiesLoader recipiesLoader;

    public RecipeEnergyValue getRecipeEnergyValue(String recipeId) {
        Long id = null;
        id = LongUtils.getLongFromString(recipeId);

        return recipiesModel.getRecipeEnergyValueForRecipe(id);
    }

    public RecipeProductInfoResponse getRecipeProductInfoResponse(String recipeId) {
        System.out.println("start getRecipeProductInfoResponse");
        Long id = null;
        id = LongUtils.getLongFromString(recipeId);
        Recipe recipe = recipiesLoader.getRecipeById(id);
        if (recipe == null) {
            return null;
        }

        RecipeEnergyValue recipeEnergyValue = recipiesModel.getRecipeEnergyValueFromProducts(recipe.getRecipiesProductSet());

        System.out.println("#########size########");
        System.out.println( recipe.getRecipiesProductSet().size());
        RecipeProductInfoResponse response = new RecipeProductInfoResponse(recipeEnergyValue, recipe.getRecipiesProductSet());
        response.setRecipeEnergyValue(recipeEnergyValue);

        return response;
    }
}
