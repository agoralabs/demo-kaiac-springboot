package demo.kaiac.springboot.api.pojo;

import java.util.*;
import javax.persistence.*;


@Entity
@Table(name = "tasks")
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "task_id")
    private int id;
    @Column(name = "task_name")
    private String title;
    @Column(name = "user_id")
    private String name;
    @Column(name = "task_date")
    private String due;
    @Column(name = "task_color")
    private String status;


    public Project() {
    }

    public Project(int id, String title, String name, String due, String status) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.due = due;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}