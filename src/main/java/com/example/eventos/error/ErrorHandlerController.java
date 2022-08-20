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

        if (e == null) {
            model.addAttribute("errorDetail", "La pagina no existe.");
        }
        else{
            model.addAttribute("errorDetail", e.getMessage());
        }
        return "error";
    }
}
