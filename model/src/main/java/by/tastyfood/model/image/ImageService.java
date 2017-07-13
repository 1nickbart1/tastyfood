package by.tastyfood.model.image;

import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.dao.entity.SliderImages;
import by.tastyfood.dao.i.IImageDao;
import by.tastyfood.dao.i.ISliderImagesDao;
import by.tastyfood.recepies.RecipeImgDto;
import org.apache.log4j.Logger;
import org.hibernate.mapping.Set;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nikolay on 01.06.2017.
 */
@Service
public class ImageService {
    private static final Logger log = Logger.getLogger(ImageService.class);

    private static int MAX_SIZE = 400000;

    private static int IMG_WIDTH = 650;

    private static int IMG_HEIGHT = 650;

    @Autowired
    private IImageDao imageDao;
    @Autowired
    private ISliderImagesDao sliderImagesDao;


    public Long saveImg(ImageEntity image) {

        if (image.getImage().length > MAX_SIZE) {

            try {
                resizeImage(image);

            } catch (IOException e) {
                log.error(e);
                log.error("img cant be saved!");
                return null;
            }
        }

        Long id = imageDao.add(image);
        log.info("img id = " + id);

        return id;

    }

    public ImageEntity getImageById(Long id) {

        return imageDao.get(id);
    }

    public void updateImage(ImageEntity image) {
        if (image.getImage().length > MAX_SIZE) {

            try {
                resizeImage(image);

            } catch (IOException e) {
                log.error(e);
                log.error("img cant be saved!");
                return;
            }
        }

        imageDao.update(image);
    }

    public void deleteImage(Long imageId) {
        ImageEntity image = new ImageEntity();
        image.setId(imageId);
        deleteImage(image);

    }

    public List<ImageEntity> getSliderImages(Long sliderId) {
        if (sliderId == null) {
            return new ArrayList<>();
        }

        return imageDao.getAllImagesForSlider(sliderId);
    }

    public ImageEntity getImageById(String id) {

        Long longId = null;
        try {
            longId = Long.parseLong(id);

        } catch (Exception e) {
            log.error(e);
        }

        if (longId == null) {
            return null;
        }

        return imageDao.get(longId);
    }

    public void resizeImage(ImageEntity image) throws IOException {
        BufferedImage img = null;

        img = ImageIO.read(new ByteArrayInputStream(image.getImage()));
        BufferedImage scaledImg = Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, IMG_WIDTH, IMG_HEIGHT);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(scaledImg, "jpg", baos);

        image.setImage(baos.toByteArray());
    }

    public void deleteImage(ImageEntity image) {
//        Collection<SliderImages> sliders = image.getSliderImages();
        imageDao.delete(image);

//        try {
//            sliders.forEach(slider -> sliderImagesDao.delete(slider));
//        }catch (Exception e){
//            log.error(e);
//        }
    }


    public void deleteUnusedImages(List<ImageEntity> allImages, Collection<String> newImagesNames) {
        for (ImageEntity image : allImages) {
            if (!newImagesNames.contains(image.getName())) {
                deleteImage(image);

            }
        }
    }

    public void deleteUnusedImages(List<ImageEntity> allImages, RecipeImgDto recipeImgDto) {
        deleteUnusedImages(allImages, recipeImgDto.getImageBytemap().keySet());
    }

    public long saveOrUpdateImage(ImageEntity newImage, List<ImageEntity> oldImages) {
        long imgId;
        if (oldImages.contains(newImage)) {

            ImageEntity oldImage = oldImages.get(oldImages.indexOf(newImage));
            oldImage.setDescription(newImage.getDescription());
            oldImage.setImage(newImage.getImage());
            oldImage.setName(newImage.getName());
            oldImage.setSliderImages(newImage.getSliderImages());

            imgId = oldImage.getId();
//            newImage.setId(imgId);
            updateImage(oldImage);
        } else {
            imgId = saveImg(newImage);
        }

        return imgId;
    }


}
