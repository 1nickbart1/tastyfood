package by.tastyfood.jsf.beans.request;

import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.jsf.utils.JsfUtils;
import by.tastyfood.model.image.ImageService;
import by.tastyfood.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Nikolay on 01.06.2017.
 */
@Component
@ManagedBean(name = "fileUploadBean")
@RequestScoped
@Scope("request")
public class FileUploadBean {
    private Part file;

    @Autowired
    private ImageService imageService;


    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void saveImg() {
        System.out.println("start saveImg ");

        if(file == null){
            return;
        }


            byte[] bytes = JsfUtils.getBytesFromHttpPart(file);

            ImageEntity image = new ImageEntity();
            image.setImage(bytes);

            String fileName = StringUtils.findValueInString(file.getHeader("content-disposition"),"filename");

            image.setName(fileName);
            image.setDescription("test desc");



            System.out.println();



            imageService.saveImg(image);



        file = null;
    }

}
