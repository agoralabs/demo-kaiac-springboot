package demo.kaiac.springboot.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.v3.oas.annotations.tags.Tag;

import demo.kaiac.springboot.api.pojo.Project;
import demo.kaiac.springboot.api.repository.ProjectRepository;

@Tag(name = "Api Demo Controller", description = "Demo management APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ApiDemoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public List<Project> getProjects() {
        log.debug("Inside of getProjects() method ");
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project(1,"IMDB","Cathy Coulaly","1st June 2023","overdue"));
        projectList.add(new Project(2,"Frontend","Joseph Future","20th June 2023","ongoing"));
        projectList.add(new Project(3,"Backend","Charles Pika","10th June 2023","completed"));
        projectList.add(new Project(4,"Database","Wesley Weezy","1st July 2023","ongoing"));
        return projectList;
    }

    @PostMapping(value = "/project")
    public Project createProduct() {
        log.debug("Inside of createProduct() method ");

        return new Project(5,"New Project","Project is saved successfully","1st July 2023","ongoing");
    }

    @GetMapping(path="/projects/all")
    public @ResponseBody Iterable<Project> getAllUsers() {
        // This returns a JSON or XML with the projects
        return projectRepository.findAll();
    }

}