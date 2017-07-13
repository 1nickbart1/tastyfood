package by.tastyfood.model.Recipies;

import by.tastyfood.dao.entity.*;
import by.tastyfood.dao.i.IFavoriteRecipeDao;
import by.tastyfood.dao.i.IRecipeRatingDao;
import by.tastyfood.model.ProductUnitsEnum;
import by.tastyfood.model.security.UserService;
import by.tastyfood.recepies.RecipeEnergyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Nikolay on 06.06.2017.
 */
@Service
public class RecipiesModel {
    @Autowired
    IRecipeRatingDao recipeRatingDao;
    @Autowired
    UserService userService;
    @Autowired
    IFavoriteRecipeDao favoriteRecipeDao;

    public RecipeEnergyValue getRecipeEnergyValueFromProducts(Set<RecipiesProduct> productSet) {

        RecipeEnergyValue recipeEnergyValue = new RecipeEnergyValue();

        for (RecipiesProduct product : productSet) {
            addEnergyValueToRecipeFromProduct(recipeEnergyValue, product);
        }

        return recipeEnergyValue;

    }


    public void addEnergyValueToRecipeFromProduct(RecipeEnergyValue recipeEnergyValue, RecipiesProduct product) {

        Double productProteins = product.getProduct().getProductDescription().getProteins();
        Double productCarbohydrates = product.getProduct().getProductDescription().getCarbohydrates();
        Double productFat = product.getProduct().getProductDescription().getFat();
        Double productCalories = product.getProduct().getProductDescription().getCalories();

        double multipFactorForProduct = product.getUnits() * product.getProduct().getProductUnit().getMultipyFactor();

        if (productProteins == null) {
            recipeEnergyValue.setProteinsCanBeCalculated(false);
        } else {
            recipeEnergyValue.addProteins(productProteins * multipFactorForProduct);
        }

        if (productCarbohydrates == null) {
            recipeEnergyValue.setProteinsCanBeCalculated(false);
        } else {
            recipeEnergyValue.addCarbohydrates(productCarbohydrates * multipFactorForProduct);
        }

        if (productFat == null) {
            recipeEnergyValue.setFatCanBeCalculated(false);
        } else {
            recipeEnergyValue.addFat(productFat * multipFactorForProduct);
        }

        if (productCalories == null) {
            recipeEnergyValue.setCaloriesCanBeCalculated(false);
        } else {
            recipeEnergyValue.addCalories(productCalories * multipFactorForProduct);

        }
    }

    public List<String> getNamesVotedUsers(Recipe recipe) {
        List<String> destList = new ArrayList<>();
        recipe.getRecipeRatings().forEach(recipeRating ->
                destList.add(recipeRating.getUser().getDisplayName()));

        return destList;
    }

    public void saveRating(RecipeRating recipeRating) {
        if (recipeRating.getId() != null) {
            recipeRatingDao.update(recipeRating);
        } else {
            recipeRatingDao.add(recipeRating);
        }
    }

    public RecipeRating getRecipeRatingForUser(Recipe recipe, String username) {
        Set<RecipeRating> allRatings = recipe.getRecipeRatings();
        RecipeRating destRecipeRating = allRatings.stream().filter(recipeRating -> recipeRating.getUser()
                .getUsername().equals(username)).findFirst()
                .orElse(new RecipeRating(userService.findByUsername(username), recipe));

        return destRecipeRating;
    }

    public void saveOrUpDateFavoriteRecipe(Recipe recipe, Users user) {
        FavoriteRecipe favRecForSave = new FavoriteRecipe();
        favRecForSave.setUser(user);
        favRecForSave.setRecipe(recipe);

        FavoriteRecipe existFavoriteRecipe =findFavoriteRecipeForUser(recipe, user);

        if (existFavoriteRecipe == null) {
            Long id = favoriteRecipeDao.add(favRecForSave);
            favRecForSave.setId(id);
            user.getFavoriteRecipes().add(favRecForSave);
        } else {
            user.getFavoriteRecipes().remove(existFavoriteRecipe);
//            recipe.getFavoriteRecipes().remove(existFavoriteRecipe);
            favoriteRecipeDao.delete(existFavoriteRecipe);
        }
    }

    public FavoriteRecipe findFavoriteRecipeForUser(Recipe recipe, Users user){
        if(user == null || recipe == null){
            return null;
        }

        FavoriteRecipe favRecForSave = new FavoriteRecipe();
        favRecForSave.setUser(user);
        favRecForSave.setRecipe(recipe);

        FavoriteRecipe existFavoriteRecipe =
                user.getFavoriteRecipes().stream().filter(favRec ->
                        favRec.getUser().getId().equals(favRecForSave.getUser().getId())
                                && favRec.getRecipe().getId().equals(favRecForSave.getRecipe().getId())
                ).findFirst().orElse(null);

        return existFavoriteRecipe;
    }


    public List<Recipe> getFavoriteRecipies(String userName){
       Set<FavoriteRecipe> favoriteRecipes = userService.findByUsername(userName).getFavoriteRecipes();
       List<Recipe>  destList = new ArrayList<>();

        favoriteRecipes.forEach( favRec ->
                destList.add(favRec.getRecipe()));

        return destList;
    }

}
