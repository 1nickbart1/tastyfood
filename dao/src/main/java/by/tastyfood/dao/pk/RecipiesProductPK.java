package by.tastyfood.dao.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Embeddable
public class RecipiesProductPK implements Serializable {
    @Column(name = "recipies_id")
    private Long recipeId;

    @Column(name = "product_id")
    private Long productId;


    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
