package by.tastyfood.controllers;

import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.model.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nikolay on 20.07.2017.
 */

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String testApi(){
        System.out.println("mvc controller");
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@RequestParam("id") String id){

       ImageEntity img = imageService.getImageById(id);

        if(img == null){
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
       ResponseEntity<byte[]> responseEntity = new ResponseEntity(img.getImage(), headers, HttpStatus.OK);

       return responseEntity;

    }
}
