package by.tastyfood.controllers;

import by.tastyfood.model.Recipies.RecipeControllerHandler;
import by.tastyfood.recepies.RecipeEnergyValue;
//import com.fasterxml.jackson.databind.ObjectMapper;
import by.tastyfood.recepies.response.RecipeProductInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Nikolay on 23.07.2017.
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeControllerHandler recipeControllerHandler;

    @ResponseBody
    @RequestMapping(value = "/energyValue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeEnergyValue getRecipeEnergyValue(@RequestParam String recipeId) {
       return recipeControllerHandler.getRecipeEnergyValue(recipeId);

    }

    @ResponseBody
    @RequestMapping(value = "/recipeProductInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeProductInfoResponse getRecipeProductInfoResponse(@RequestParam String recipeId) {
        return recipeControllerHandler.getRecipeProductInfoResponse(recipeId);

    }
}
