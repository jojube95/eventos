package com.example.eventos.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {

    private static final String ATTRIBUTE_ERROR_DETAIL = "errorDetail";

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Exception e = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();

        model.addAttribute("statusCode", statusCode);

        if (e == null) {
            switch (statusCode){
                case "404":
                    model.addAttribute(ATTRIBUTE_ERROR_DETAIL, "La pagina no existe.");
                    break;
                case "403":
                    model.addAttribute(ATTRIBUTE_ERROR_DETAIL, "No tiene permisos suficientes.");
                    break;
                default:
                    model.addAttribute(ATTRIBUTE_ERROR_DETAIL, "Ha habido un error.");
                    break;
            }
        }
        else{
            model.addAttribute(ATTRIBUTE_ERROR_DETAIL, e.getMessage());
        }
        return "error";
    }
}
