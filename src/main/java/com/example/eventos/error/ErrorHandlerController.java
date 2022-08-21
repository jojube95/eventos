package com.example.eventos.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Exception e = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();

        model.addAttribute("statusCode", statusCode);

        if (e == null) {
            switch (statusCode){
                case "404":
                    model.addAttribute("errorDetail", "La pagina no existe.");
                    break;
                case "403":
                    model.addAttribute("errorDetail", "No tiene permisos suficientes.");
                    break;
                default:
                    model.addAttribute("errorDetail", "Ha habido un error.");
                    break;
            }
        }
        else{
            model.addAttribute("errorDetail", e.getMessage());
        }
        return "error";
    }
}
