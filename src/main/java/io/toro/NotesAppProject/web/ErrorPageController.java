package io.toro.NotesAppProject.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.toro.NotesAppProject.pojo.ApiError;

@RestController
public class ErrorPageController implements ErrorController {

    private static final String PATH = "/error";
    private ErrorAttributes errorAttributes;

    private Map<String,Object> getErrorAttribute(HttpServletRequest req, boolean includeStackTrace){
        RequestAttributes requestAttributes = new ServletRequestAttributes(req);
        return errorAttributes.getErrorAttributes(requestAttributes,includeStackTrace);

    }

    @Autowired
    public ErrorPageController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }


    @GetMapping(PATH)
    ApiError error(HttpServletRequest req, HttpServletResponse res){
        return new ApiError(res.getStatus(), getErrorAttribute(req, true));
    }
}
