package by.tastyfood.enums;

/**
 * Created by Nikolay on 09.06.2017.
 */
public enum ParamNamesEnum {
   PRODUCT_ID("prodId"),
    RECIPE_ID("recipeId"),
    CATEGORY_ID("catId"),
    FAVORITE("favorite");

   private String param;


    private ParamNamesEnum(String param){
       this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
