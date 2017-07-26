package by.tastyfood.recepies.response;

import by.tastyfood.dao.entity.RecipiesProduct;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Nikolay on 25.07.2017.
 */
@JsonSerialize
public class RecipeProductResponse {
    @JsonProperty
    private String productName;
    @JsonProperty
    private Long productId;

    @JsonProperty
    private double units;

    public RecipeProductResponse(){
    }
    public RecipeProductResponse(RecipiesProduct recipiesProduct){
        this.productName = recipiesProduct.getProduct().getName();
        this.units = recipiesProduct.getUnits();
        this.productId = recipiesProduct.getProduct().getId();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
