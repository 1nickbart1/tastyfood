package by.tastyfood.recepies.response;

import by.tastyfood.dao.entity.RecipiesProduct;
import by.tastyfood.recepies.RecipeEnergyValue;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikolay on 25.07.2017.
 */
@JsonSerialize
public class RecipeProductInfoResponse {
    @JsonProperty
    private RecipeEnergyValue recipeEnergyValue;
    @JsonProperty
    private Set<RecipeProductResponse> recipiesProductSet;

    public RecipeProductInfoResponse(){
        recipiesProductSet = new HashSet<RecipeProductResponse>();
    }
    public RecipeProductInfoResponse(RecipeEnergyValue recipeEnergyValue, Set<RecipiesProduct> recipiesProductSet){
        this.recipeEnergyValue = recipeEnergyValue;

        this.recipiesProductSet = new HashSet<RecipeProductResponse>();
        for(RecipiesProduct recipiesProduct: recipiesProductSet){
            System.out.println("-----recipiesProduct------");
            System.out.println(recipiesProduct.getProduct().getName());
            this.recipiesProductSet.add(new RecipeProductResponse(recipiesProduct));
        }
    }

    public RecipeEnergyValue getRecipeEnergyValue() {
        return recipeEnergyValue;
    }

    public void setRecipeEnergyValue(RecipeEnergyValue recipeEnergyValue) {
        this.recipeEnergyValue = recipeEnergyValue;
    }

    public Set<RecipeProductResponse> getRecipiesProductSet() {
        return recipiesProductSet;
    }

    public void setRecipiesProductSet(Set<RecipeProductResponse> recipiesProductSet) {
        this.recipiesProductSet = recipiesProductSet;
    }
}
