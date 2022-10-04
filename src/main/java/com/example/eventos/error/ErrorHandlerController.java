package com.example.eventos.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import static com.example.eventos.config.Constants.ATTRIBUTE_ERROR_DETAIL;

@Controller
public class ErrorHandlerController implements ErrorController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Exception e = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();

        model.addAttribute("statusCode", statusCode);

        if (e == null) {
            switch (statusCode){
                case "404":
                    model.addAttribute(ATTRIBUTE_ERROR_DETAIL, messageSource.getMessage("paginaNoExiste", null,
                            LocaleContextHolder.getLocale()));
                    break;
                case "403":
                    model.addAttribute(ATTRIBUTE_ERROR_DETAIL, messageSource.getMessage("noTienePermisos", null,
                            LocaleContextHolder.getLocale()));
                    break;
                default:
                    model.addAttribute(ATTRIBUTE_ERROR_DETAIL, messageSource.getMessage("haHabidoError", null,
                            LocaleContextHolder.getLocale()));
                    break;
            }
        }
        else{
            model.addAttribute(ATTRIBUTE_ERROR_DETAIL, e.getMessage());
        }
        return "error";
    }
}
