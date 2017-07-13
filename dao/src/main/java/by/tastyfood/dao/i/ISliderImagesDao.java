package by.tastyfood.dao.i;

import by.tastyfood.dao.IDao;
import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.dao.entity.SliderImages;

import java.util.List;

/**
 * Created by Nikolay on 13.06.2017.
 */
public interface ISliderImagesDao extends IDao<SliderImages,Long> {
    public void delete(ImageEntity image, long sliderId);


}
