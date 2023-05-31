package ru.itis.judgeassistant.controllers.templ;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.judgeassistant.dto.ExceptionDto;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request,
                              Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("message", exception == null ? "No message": exception.getMessage());

        String contentType = request.getHeader("Content-Type");

        if (contentType != null && contentType.equals("application/json")) {
            return "forward:/error/json/" + statusCode;
        }
        if (statusCode == 404) {
            return "errors/error-404";
        } else if (statusCode >= 500) {
            return "errors/error-5xx";
        }

        return "errors/error";
    }

    @RequestMapping("/error/json/{status-code}")
    @ResponseBody
    private ExceptionDto handleJSON(@PathVariable("status-code") Integer code,
                                    HttpServletRequest request) {

        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        String message =  exception == null ? "No message": exception.getMessage();
        return ExceptionDto.builder()
                .status(code)
                .message(message)
                .build();
    }

}
