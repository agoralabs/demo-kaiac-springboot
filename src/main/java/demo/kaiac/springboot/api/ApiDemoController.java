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
        Member responsible1 = new Member(1,"Cathy", "Coulaly", "/team-member-1.jpg");
        projectList.add(new Project(1,"IMDB","1st June 2023","overdue", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible1));
        Member responsible2 = new Member(2,"Joseph", "Future", "/team-member-2.jpg");
        projectList.add(new Project(2,"Frontend","20th June 2023","ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible2));
        Member responsible3 = new Member(3,"Charles", "Pika", "/team-member-3.jpg");
        projectList.add(new Project(3,"Backend","10th June 2023","completed", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible3));
        Member responsible4 = new Member(4,"Wesley", "Weezy", "/team-member-4.jpg");
        projectList.add(new Project(4,"Database","1st July 2023","ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible4));
        return projectList;
    }

    @PostMapping(value = "/project")
    public Project createProduct() {
        log.debug("Inside of createProduct() method ");
        Member responsible5 = new Member(5,"Wesley", "Weezy", "/team-member-5.jpg");
        return new Project(5,"New Project","1st July 2023","ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible5);
    }

    @GetMapping(path="/projects/all")
    public @ResponseBody Iterable<Project> getAllUsers() {
        // This returns a JSON or XML with the projects
        return projectRepository.findAll();
    }

}