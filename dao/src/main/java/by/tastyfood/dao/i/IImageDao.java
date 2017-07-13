package by.tastyfood.dao.i;

import by.tastyfood.dao.IDao;
import by.tastyfood.dao.entity.ImageEntity;

import java.util.List;

/**
 * Created by Nikolay on 01.06.2017.
 */
public interface IImageDao extends IDao<ImageEntity, Long> {
    public List<ImageEntity> getAllImagesForSlider(Long sliderId);
}
