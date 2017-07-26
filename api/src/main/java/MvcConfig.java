//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.text.SimpleDateFormat;
//import java.util.List;
//
///**
// * Created by Nikolay on 20.07.2017.
// */
//
//@EnableWebMvc
//@Configuration
//public class MvcConfig extends WebMvcConfigurationSupport {
//    @Bean
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        RequestMappingHandlerAdapter handlerMapping = super.requestMappingHandlerAdapter();
//       configureMessageConverters( handlerMapping.getMessageConverters());
//        addDefaultHttpMessageConverters(handlerMapping.getMessageConverters());
//
//
//        System.out.println("RequestMappingHandlerAdapter created");
//        return handlerMapping;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(new MappingJackson2XmlHttpMessageConverter());
//    }
//}
//
