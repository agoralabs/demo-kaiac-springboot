package demo.kaiac.springboot.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Api Demo Controller", description = "Demo management APIs")
@RestController
@RequestMapping("/api/v1")
public class ApiDemoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/products")
    public List<String> getProducts() {
        log.debug("Inside of getProducts() method ");
        List<String> productsList = new ArrayList<>();
        productsList.add("Honey");
        productsList.add("Almond");
        return productsList;
    }

    @PostMapping(value = "/products")
    public String createProduct() {
        log.debug("Inside of createProduct() method ");

        return "Product is saved successfully";
    }

}