package by.tastyfood.jsf.utils;

import by.tastyfood.dao.entity.ImageEntity;
import by.tastyfood.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Nikolay on 10.06.2017.
 */
public class JsfUtils {

    private static final Logger log = Logger.getLogger(JsfUtils.class);


    public static boolean isCurUrlContainString(String strForCheck) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri = request.getRequestURI().toString();


        if (uri == null || strForCheck == null) {
            return false;
        }

        if (uri.contains(strForCheck)) {
            return true;
        }

        return false;
    }

    public static byte[] getBytesFromHttpPart(Part part) {
        if (part == null) {
            return new byte[0];
        }

        try (InputStream input = part.getInputStream()) {
            byte[] bytes = IOUtils.toByteArray(input);
            return bytes;

        } catch (IOException e) {
            log.error(e);
        }

        return new byte[0];
    }

    public static byte[] getBytesFromFile(UploadedFile file) {
        if (file == null) {
            return new byte[0];
        }

        try (InputStream input = file.getInputstream()) {
            byte[] bytes = IOUtils.toByteArray(input);
            return bytes;

        } catch (IOException e) {
            log.error(e);
        }

        return new byte[0];
    }

    public static void showMsg(String type, String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(type,  msg));

    }
    public static void showMsg( String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  msg));

    }



}
