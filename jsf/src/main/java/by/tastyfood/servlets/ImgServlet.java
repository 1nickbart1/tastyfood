package by.tastyfood.servlets;

import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.model.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Nikolay on 01.06.2017.
 */

@WebServlet(name = "ImgServlet", urlPatterns = {"/img"})

public class ImgServlet extends HttpServlet {
    @Autowired
    private ImageService imageService;

    public void init(ServletConfig config) {

        try {
            super.init(config);

        } catch (ServletException e) {
            e.printStackTrace();
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id =req.getParameter("id");

        ImageEntity img = imageService.getImageById(id);

        if(img == null){

            return;
        }

        resp.setContentType("image/gif");
        OutputStream o = resp.getOutputStream();
        o.write(img.getImage());
        o.flush();
        o.close();

    }
}
