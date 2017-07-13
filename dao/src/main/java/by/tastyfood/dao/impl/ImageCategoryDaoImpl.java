package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.ImageCategory;
import by.tastyfood.dao.i.IImageCategoryDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 03.06.2017.
 */
@Service
public class ImageCategoryDaoImpl extends AbstractDaoImpl<ImageCategory, Long> implements IImageCategoryDao{

    public ImageCategoryDaoImpl(){
        super(ImageCategory.class);
    }

}
