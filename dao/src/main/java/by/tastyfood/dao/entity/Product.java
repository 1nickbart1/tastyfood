package by.tastyfood.dao.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne()
    @JoinColumn(name ="product_unit_id", nullable = true)
    private ProductUnit productUnit;

    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name ="product_description_id")
    private ProductDescription productDescription;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private Set<RecipiesProduct> recipiesProduct;

    public Set<RecipiesProduct> getRecipiesProduct() {
        return recipiesProduct;
    }

    public void setRecipiesProduct(Set<RecipiesProduct> recipiesProduct) {
        this.recipiesProduct = recipiesProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductUnit getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(ProductUnit productUnit) {
        this.productUnit = productUnit;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return productUnit != null ? productUnit.equals(product.productUnit) : product.productUnit == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (productUnit != null ? productUnit.hashCode() : 0);
        return result;
    }
}
