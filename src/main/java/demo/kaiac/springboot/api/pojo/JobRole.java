package demo.kaiac.springboot.api.pojo;

import java.util.*;
import jakarta.persistence.*;



@Entity
@Table(name = "job_roles")
public class JobRole {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "job_role_id")
    private int id;
    @Column(name = "job_role_name")
    private String name;

    public JobRole() {
    }

    public JobRole(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobRole jobRole = (JobRole) o;
        return Objects.equals(id, jobRole.getId()) &&
        Objects.equals(name, jobRole.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "JobRole [id=" + id + ", name=" + name + "]";
    }

}