package demo.kaiac.springboot.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.apache.commons.collections4;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.v3.oas.annotations.tags.Tag;

import demo.kaiac.springboot.api.pojo.JobRole;
import demo.kaiac.springboot.api.pojo.Task;
import demo.kaiac.springboot.api.pojo.User;
import demo.kaiac.springboot.api.pojo.Response;
import demo.kaiac.springboot.api.repository.JobRoleRepository;
import demo.kaiac.springboot.api.repository.TaskRepository;
import demo.kaiac.springboot.api.repository.UserRepository;

@Tag(name = "Api Demo Controller", description = "Demo management APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ApiDemoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRoleRepository jobRoleRepository;


    @GetMapping("/tasks")
    public @ResponseBody Response getTasks() {
        log.debug("Inside of getTasks() method ");

        boolean status;
        String message = "Tasks";
        List<Task> taskList = new ArrayList<>();

        try {
            //  Block of code to try
            // This returns a JSON or XML with the projects
            status = true;
            taskList = IterableUtils.toList(taskRepository.findAll());
            
        }
        catch(Exception e) {
            //  Block of code to handle errors
            status = false;
            User responsible1 = new User(1, "Cathy", "Coulaly", "cathy.coulaly@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-1.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10");
            taskList.add(new Task(1, "SB-IMDB", "2", "overdue", "Lorem ipsum dolor sit amet consectetur adipisicing elit", "2023-08-25 20:10:11", "2023-08-25 20:10:11", "2023-08-25 20:10:11", responsible1));
            
            User responsible2 = new User(2, "Joseph", "Future", "joseph.future@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-2.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10");
            taskList.add(new Task(2, "SB-Frontend", "2", "ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit", "2023-08-25 20:10:11", "2023-08-25 20:10:11", "2023-08-25 20:10:11", responsible2));
            
            User responsible3 = new User(3, "Charles", "Pika", "charles.pika@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-3.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10");
            taskList.add(new Task(3, "SB-Backend", "2", "completed", "Lorem ipsum dolor sit amet consectetur adipisicing elit", "2023-08-25 20:10:11", "2023-08-25 20:10:11", "2023-08-25 20:10:11", responsible3));
            
            User responsible4 = new User(4, "Wesley", "Weezy", "wesley.weezy@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-4.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10");
            taskList.add(new Task(4, "SB-Database", "2", "ongoing", "Lorem ipsum dolor sit amet consectetur adipisicing elit", "2023-08-25 20:10:11", "2023-08-25 20:10:11", "2023-08-25 20:10:11", responsible4));
            
        }

        return new Response(status, message, taskList);

    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        log.debug("Inside of getAllUsers() method ");
        try {
            // This returns a JSON or XML with the users
            return userRepository.findAll();
        }
        catch(Exception e) {
            //  Block of code to handle errors
            List<User> userList = new ArrayList<>();
            Set<JobRole> jobRoles1 = new HashSet<>();
            jobRoles1.add(new JobRole(1, "Team Lead", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            userList.add(new User(1, "SB-Cathy", "Coulaly", "cathy.coulaly@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-1.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10",jobRoles1));
            Set<JobRole> jobRoles2 = new HashSet<>();
            jobRoles2.add(new JobRole(2, "Frontend Developer", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            userList.add(new User(2, "SB-Joseph", "Future", "joseph.future@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-2.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10",jobRoles2));
            Set<JobRole> jobRoles3 = new HashSet<>();
            jobRoles3.add(new JobRole(3, "Backend Developer", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            userList.add(new User(3, "SB-Charles", "Pika", "charles.pika@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-3.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10",jobRoles3));
            Set<JobRole> jobRoles4 = new HashSet<>();
            jobRoles4.add(new JobRole(4, "Database Admin", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            userList.add(new User(4, "SB-Wesley", "Weezy", "wesley.weezy@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-4.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10",jobRoles4));
            Set<JobRole> jobRoles5 = new HashSet<>();
            jobRoles5.add(new JobRole(5, "Full Stack Developer", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            userList.add(new User(5, "SB-Marie", "Jose", "marie.jo@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-5.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10",jobRoles5));
            Set<JobRole> jobRoles6 = new HashSet<>();
            jobRoles6.add(new JobRole(6, "Sys Admin", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            userList.add(new User(6, "SB-Charles", "Pika", "charles.pika@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-6.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10",jobRoles6));
            Set<JobRole> jobRoles7 = new HashSet<>();
            jobRoles7.add(new JobRole(7, "Product Owner", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            userList.add(new User(7, "SB-Boris", "John", "boris.john@agoralabs.org", "2023-08-25 20:10:10", "$2y$10$NBgiGMf8cQOoTjf5Xefz4OuI.Yabsc461/ArxpUL138IGnPSZtda6", "/team-member-7.jpg", null, "2023-08-25 20:10:10", "2023-08-25 20:10:10",jobRoles7));
            
            return userList;
        }

    }

    @GetMapping(path="/job_roles")
    public @ResponseBody Iterable<JobRole> getAllJobRoles() {
        log.debug("Inside of getAllJobRoles() method ");
        try {
            //  Block of code to try
            // This returns a JSON or XML with the job_roles
            return jobRoleRepository.findAll();
        }
        catch(Exception e) {
            //  Block of code to handle errors
            List<JobRole> jobRoleList = new ArrayList<>();
            jobRoleList.add(new JobRole(1, "SB-Team Lead", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            jobRoleList.add(new JobRole(2, "SB-Frontend Developer", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            jobRoleList.add(new JobRole(3, "SB-Backend Developer", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            jobRoleList.add(new JobRole(4, "SB-Database Admin", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            jobRoleList.add(new JobRole(5, "SB-Full Stack Developer", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            jobRoleList.add(new JobRole(6, "SB-Sys Admin", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            jobRoleList.add(new JobRole(7, "SB-Product Owner", "2023-08-25 20:10:10", "2023-08-25 20:10:10"));
            return jobRoleList;
        }

    }

}