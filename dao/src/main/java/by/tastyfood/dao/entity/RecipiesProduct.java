package by.tastyfood.dao.entity;

import by.tastyfood.dao.pk.RecipiesProductPK;

import javax.persistence.*;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Entity
@Table(name = "recipies_product")
public class RecipiesProduct {

    @EmbeddedId
    RecipiesProductPK pk;


    @ManyToOne
    @JoinColumn(name = "recipies_id")
    @MapsId("recipeId")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @Column(name = "units")
    private Double units;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public RecipiesProductPK getPk() {
        return pk;
    }

    public void setPk(RecipiesProductPK pk) {
        this.pk = pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipiesProduct that = (RecipiesProduct) o;

        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }


}
