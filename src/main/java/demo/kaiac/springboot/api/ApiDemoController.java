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

import demo.kaiac.springboot.api.pojo.JobRole;
import demo.kaiac.springboot.api.pojo.Project;
import demo.kaiac.springboot.api.pojo.Member;
import demo.kaiac.springboot.api.repository.JobRoleRepository;
import demo.kaiac.springboot.api.repository.ProjectRepository;
import demo.kaiac.springboot.api.repository.MemberRepository;

@Tag(name = "Api Demo Controller", description = "Demo management APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ApiDemoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JobRoleRepository jobRoleRepository;


    @GetMapping("/projects")
    public List<Project> getProjects() {
        log.debug("Inside of getProjects() method ");
        List<Project> projectList = new ArrayList<>();
        Member responsible1 = new Member(1,"Cathy", "Coulaly", "/team-member-1.jpg", "cathy.coulaly@agoralabs.org");
        projectList.add(new Project(1,"SB-IMDB","1st June 2023","overdue", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible1));
        Member responsible2 = new Member(2,"Joseph", "Future", "/team-member-2.jpg", "joseph.future@agoralabs.org");
        projectList.add(new Project(2,"SB-Frontend","20th June 2023","ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible2));
        Member responsible3 = new Member(3,"Charles", "Pika", "/team-member-3.jpg", "charles.pika@agoralabs.org");
        projectList.add(new Project(3,"SB-Backend","10th June 2023","completed", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible3));
        Member responsible4 = new Member(4,"Wesley", "Weezy", "/team-member-4.jpg", "wesley.weezy@agoralabs.org");
        projectList.add(new Project(4,"SB-Database","1st July 2023","ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible4));
        return projectList;
    }

    @PostMapping(value = "/project")
    public Project createProduct() {
        log.debug("Inside of createProduct() method ");
        Member responsible5 = new Member(5,"Dorine", "Michou", "/team-member-5.jpg", "dorine.michou@agoralabs.org");
        return new Project(5,"SB-Project5","1st July 2023","ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", responsible5);
    }

    @GetMapping(path="/allprojects")
    public @ResponseBody Iterable<Project> getAllProjects() {
        // This returns a JSON or XML with the projects
        return projectRepository.findAll();
    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<Member> getAllUsers() {
        // This returns a JSON or XML with the users
        return memberRepository.findAll();
    }

    @GetMapping(path="/jobroles")
    public @ResponseBody Iterable<JobRole> getAllJobRoles() {
        // This returns a JSON or XML with the job_roles
        return jobRoleRepository.findAll();
    }

}