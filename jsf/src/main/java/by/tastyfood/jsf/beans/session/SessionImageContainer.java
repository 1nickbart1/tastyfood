package by.tastyfood.jsf.beans.session;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikolay on 28.06.2017.
 */
@Component
@ManagedBean(name = "sessionImageContainer")
@SessionScoped
@Scope("session")
public class SessionImageContainer {
    private Map<String, byte[]> sliderImagesBytesMap;

    @PostConstruct
    public void init(){
        sliderImagesBytesMap = new HashMap<>();

    }

    public Map<String, byte[]> getSliderImagesBytesMap() {
        return sliderImagesBytesMap;
    }

    public void setSliderImagesBytesMap(Map<String, byte[]> sliderImagesBytesMap) {
        this.sliderImagesBytesMap = sliderImagesBytesMap;
    }

    public StreamedContent getStreamedTitleImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String imageKey = (String) myRequest.getParameter("image_key");

        byte[] imgBytes = sliderImagesBytesMap.get(imageKey);

        if (imgBytes == null) {
            return new DefaultStreamedContent();
        }
        DefaultStreamedContent streamImage = null;

        try {
            System.out.println("byte array size =" + imgBytes.length);

            streamImage = new DefaultStreamedContent(new ByteArrayInputStream(imgBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("stream created");

        return streamImage;

    }
}
