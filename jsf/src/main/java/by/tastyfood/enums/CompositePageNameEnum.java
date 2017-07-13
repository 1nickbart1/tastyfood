package by.tastyfood.enums;

/**
 * Created by Nikolay on 10.06.2017.
 */
public enum CompositePageNameEnum {

    PRODUCT("productEditor.xhtml"),
    RECIPE("recipeEditor.xhtml"),
    ALL_RECIPIES("allRecipesEditor.xhtml");

    private String name;


    private CompositePageNameEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
