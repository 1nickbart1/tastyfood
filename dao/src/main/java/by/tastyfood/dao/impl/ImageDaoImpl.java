package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.dao.i.IImageDao;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nikolay on 01.06.2017.
 */
@Service
public class ImageDaoImpl extends AbstractDaoImpl<ImageEntity, Long> implements IImageDao {

    public ImageDaoImpl(){

        super(ImageEntity.class);
    }

    @SuppressWarnings("JpaQlInspection")
    public List<ImageEntity> getAllImagesForSlider(Long sliderId) {
        Query query =getSession().createQuery("select img from ImageEntity img join fetch img.sliderImages  sldr" +
                " where img.id = sldr.image and sldr.sliderId = :sliderId")
                .setLong("sliderId", sliderId);
        List<ImageEntity> images = (List<ImageEntity>) query.list();

        return images;
    }
}
