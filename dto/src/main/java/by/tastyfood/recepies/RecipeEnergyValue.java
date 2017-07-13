package by.tastyfood.recepies;

/**
 * Created by Nikolay on 06.06.2017.
 */
public class RecipeEnergyValue {

    private Double proteins;
    private Double carbohydrates;
    private Double fat;
    private Double calories;
    private boolean proteinsCanBeCalculated =true;
    private boolean carbohydratesCanBeCalculated =true;
    private boolean fatCanBeCalculated =true;
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
