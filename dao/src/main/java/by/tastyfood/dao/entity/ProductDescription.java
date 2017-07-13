package by.tastyfood.dao.entity;

import javax.persistence.*;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Entity()
@Table(name = "product_description")
public class ProductDescription {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "proteins", nullable = false)
    private Double proteins;

    @Column(name = "carbohydrates", nullable = false)
    private Double carbohydrates;

    @Column(name = "fat", nullable = false)
    private Double fat;

    @Column(name = "calories", nullable = false)
    private Double calories;

    @OneToOne(mappedBy = "productDescription")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
