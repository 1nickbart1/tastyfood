package by.tastyfood.dao.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Nikolay on 01.06.2017.
 */
@Entity
@Table(name = "image")
public class ImageEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = true, length = 50)
    private String description;

    @Type(type = "binary")
    @Basic(fetch=FetchType.LAZY)
    @Column( length = 1000000 )
    private byte[] image;

    @ManyToOne()
    @JoinColumn(name ="imgCategories_id", nullable = true)
    private ImageCategory category;

    @OneToMany(mappedBy = "image", cascade = CascadeType.REMOVE)
    private Set<SliderImages> sliderImages;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "image")
//    private RecipiesCategory recipiesCategory;

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

//    public RecipiesCategory getRecipiesCategory() {
//        return recipiesCategory;
//    }
//
//    public void setRecipiesCategory(RecipiesCategory recipiesCategory) {
//        this.recipiesCategory = recipiesCategory;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ImageCategory getCategory() {
        return category;
    }

    public void setCategory(ImageCategory category) {
        this.category = category;
    }

    public Set<SliderImages> getSliderImages() {
        return sliderImages;
    }

    public void setSliderImages(Set<SliderImages> sliderImages) {
        this.sliderImages = sliderImages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageEntity that = (ImageEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

}
