package by.tastyfood.recepies;


import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by Nikolay on 06.06.2017.
 */
@JsonSerialize
public class RecipeEnergyValue implements Serializable{

    @JsonProperty
    private Double proteins;
    @JsonProperty
    private Double carbohydrates;
    @JsonProperty
    private Double fat;
    @JsonProperty
    private Double calories;
    @JsonProperty
    private boolean proteinsCanBeCalculated =true;
    @JsonProperty
    private boolean carbohydratesCanBeCalculated =true;
    @JsonProperty
    private boolean fatCanBeCalculated =true;
    @JsonProperty
    private boolean caloriesCanBeCalculated =true;

    public RecipeEnergyValue(){
         proteins = 0D;
         carbohydrates =0D;
         fat= 0D;
         calories = 0D;

    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public boolean isProteinsCanBeCalculated() {
        return proteinsCanBeCalculated;
    }

    public void setProteinsCanBeCalculated(boolean proteinsCanBeCalculated) {
        this.proteinsCanBeCalculated = proteinsCanBeCalculated;
    }

    public boolean isCarbohydratesCanBeCalculated() {
        return carbohydratesCanBeCalculated;
    }

    public void setCarbohydratesCanBeCalculated(boolean carbohydratesCanBeCalculated) {
        this.carbohydratesCanBeCalculated = carbohydratesCanBeCalculated;
    }

    public boolean isFatCanBeCalculated() {
        return fatCanBeCalculated;
    }

    public void setFatCanBeCalculated(boolean fatCanBeCalculated) {
        this.fatCanBeCalculated = fatCanBeCalculated;
    }

    public boolean isCaloriesCanBeCalculated() {
        return caloriesCanBeCalculated;
    }

    public void setCaloriesCanBeCalculated(boolean caloriesCanBeCalculated) {
        this.caloriesCanBeCalculated = caloriesCanBeCalculated;
    }

    public void addCarbohydrates(Double carbohydrates) {
        this.carbohydrates += carbohydrates;
    }
    public void addProteins(Double proteins) {
        this.proteins += proteins;
    }
    public void addFat(Double fat) {
        this.fat += fat;
    }
    public void addCalories(Double calories) {
        this.calories += calories;
    }
}
