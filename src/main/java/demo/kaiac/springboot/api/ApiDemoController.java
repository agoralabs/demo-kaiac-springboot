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

import demo.kaiac.springboot.api.pojo;

@Tag(name = "Api Demo Controller", description = "Demo management APIs")
@RestController
@RequestMapping("/api/v1")
public class ApiDemoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/projects")
    public List<String> getProjects() {
        log.debug("Inside of getProjects() method ");
        List<String> productsList = new ArrayList<>();
        productsList.add(new Project(1,'IMDB','Cathy Coulaly','1st June 2023','overdue'));
        productsList.add(new Project(2,'Frontend','Joseph Future','20th June 2023','ongoing'));
        productsList.add(new Project(3,'Backend','Charles Pika','10th June 2023','completed'));
        productsList.add(new Project(4,'Database','Wesley Weezy','1st July 2023','ongoing'));
        return productsList;
    }

    @PostMapping(value = "/project")
    public String createProduct() {
        log.debug("Inside of createProduct() method ");

        return "Product is saved successfully";
    }

}