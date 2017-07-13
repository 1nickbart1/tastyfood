package by.tastyfood.dao.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nikolay on 03.06.2017.
 */
@Entity
@Table(name = "reciepies_category")
public class RecipiesCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

//    @OneToOne()
//    @JoinColumn(name="image_id")
//    private ImageEntity image;

    @Column(name = "image_id", nullable = false, length = 50)
    private Long imageId;

    @OneToMany(mappedBy = "recipiesCategory",fetch = FetchType.EAGER)
    private Set<Recipe> recipeSet;

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

//    public ImageEntity getImage() {
//        return image;
//    }
//
//    public void setImage(ImageEntity image) {
//        this.image = image;
//    }

    public Set<Recipe> getRecipeSet() {
        return recipeSet;
    }

    public void setRecipeSet(Set<Recipe> recipeSet) {
        this.recipeSet = recipeSet;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
