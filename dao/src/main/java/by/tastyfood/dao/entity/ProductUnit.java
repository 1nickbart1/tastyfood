package by.tastyfood.dao.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Entity
@Table(name = "product_unit")
public class ProductUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "multipy_factor", nullable = false)
    private Double multipyFactor;

    @OneToMany(mappedBy = "productUnit")
    private Set<Product> productSet;

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

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public Double getMultipyFactor() {
        return multipyFactor;
    }

    public void setMultipyFactor(Double multipyFactor) {
        this.multipyFactor = multipyFactor;
    }
}
