package by.tastyfood.dao.entity;

import javax.persistence.*;

/**
 * Created by Nikolay on 08.06.2017.
 */
@Entity
@Table(name = "slider_images")
public class SliderImages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "slider_id", nullable = false, length = 50)
    private Long sliderId;


//    @Column(name = "image_id", nullable = false, length = 50)
//    private Long imageId;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", nullable = false)
    private ImageEntity image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSliderId() {
        return sliderId;
    }

    public void setSliderId(Long sliderId) {
        this.sliderId = sliderId;
    }

//    public Long getImageId() {
//        return imageId;
//    }
//
//    public void setImageId(Long imageId) {
//        this.imageId = imageId;
//    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SliderImages that = (SliderImages) o;

        if (sliderId != null ? !sliderId.equals(that.sliderId) : that.sliderId != null) return false;
        return image.getId() != null ? image.getId().equals(that.image.getId()) : that.image.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = sliderId != null ? sliderId.hashCode() : 0;
        result = 31 * result + (image.getId() != null ? image.getId().hashCode() : 0);
        return result;
    }
}
