package by.tastyfood.recepies;

import by.tastyfood.dao.entity.Recipe;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nikolay on 13.06.2017.
 */
public class RecipeImgDto {

    private Recipe recipe;
    private Map<String,byte[]> imageBytemap;
    private  String titleImagename;
    private Long sliderId;


    public RecipeImgDto(Recipe recipe, Map<String, byte[]> imageBytemap, String titleImagename) {
        this.recipe = recipe;
        this.imageBytemap = imageBytemap;
        this.titleImagename = titleImagename;
    }
    public RecipeImgDto(){

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Map<String, byte[]> getImageBytemap() {
        return imageBytemap;
    }

    public void setImageBytemap(Map<String, byte[]> imageBytemap) {
        this.imageBytemap = imageBytemap;
    }

    public String getTitleImagename() {
        return titleImagename;
    }

    public void setTitleImagename(String titleImagename) {
        this.titleImagename = titleImagename;
    }

    public Long getSliderId() {
        return sliderId;
    }

    public void setSliderId(Long sliderId) {
        this.sliderId = sliderId;
    }

    public List<String> findErrors(){
        List<String> errList = new ArrayList<String>();

        if(StringUtils.isEmpty(recipe.getTitle()) ){
            errList.add("Нужно ввести название статьи");
        }
        if(StringUtils.isEmpty(titleImagename) ){
            errList.add("Нужно выбрать заглавное фото");
        }
        if(recipe.getRecipiesProductSet().size() == 0 ){
            errList.add("Нужно указать продукты");
        }
        if(recipe.getCookTime() == 0 ){
            errList.add("Нужно указать время готовки");
        }
        if(StringUtils.isEmpty(recipe.getText())){
            errList.add("Нужно написать статью");
        }

        return  errList;

    }
}
