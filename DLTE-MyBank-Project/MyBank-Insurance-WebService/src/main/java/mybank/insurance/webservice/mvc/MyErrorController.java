package mybank.insurance.webservice.mvc;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) throws UnsupportedEncodingException {
        Integer errorCode;
        String errorMessage = null;
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object statusMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (status != null) {
            errorCode = Integer.valueOf(status.toString());
            errorMessage = String.valueOf(statusMessage.toString());
            if (errorCode == HttpStatus.NOT_FOUND.value() || errorCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "redirect:../error?code=" + errorCode + "&message=" + URLEncoder.encode(errorMessage, "UTF-8");
            }
        }
        return "error";
    }
}

