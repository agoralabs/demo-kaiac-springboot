package demo.kaiac.springboot.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.v3.oas.annotations.tags.Tag;

import demo.kaiac.springboot.api.pojo.Token;

@Tag(name = "Login Demo Controller", description = "Demo Login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginDemoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/login")
    public @ResponseBody Token getToken() {
        log.debug("Inside of getToken() method ");

        Token token = new Token("1|rb1x2kFmX9hdveiEVymfhjaLpfhjXEkla5v4iAcH", "Bearer");
        return token;

    }


}