package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.dao.entity.SliderImages;
import by.tastyfood.dao.i.ISliderImagesDao;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay on 13.06.2017.
 */
@Service
public class SliderImagesDao extends AbstractDaoImpl<SliderImages, Long> implements ISliderImagesDao {

    public SliderImagesDao(){
        super(SliderImages.class);
    }

    @SuppressWarnings("JpaQlInspection")
    public void delete(ImageEntity imageForDelete, long sliderId) {
       Query query = getSession().createQuery("delete from  SliderImages sldr  where sldr.image = :imageForDelete and sldr.sliderId = :sliderId")
                .setParameter("imageForDelete", imageForDelete).setLong("sliderId", sliderId);

        query.executeUpdate();
    }
}
