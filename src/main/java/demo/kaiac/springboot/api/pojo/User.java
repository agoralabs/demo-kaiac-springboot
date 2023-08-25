package demo.kaiac.springboot.api.pojo;

import java.util.*;
import jakarta.persistence.*;



@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "email_verified_at")
    private String email_verified_at;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "remember_token")
    private String remember_token;
    @Column(name = "created_at")
    private String created_at;
    @Column(name = "updated_at")
    private String updated_at;
    @ManyToMany
    @JoinTable(
    name = "user_job_roles", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "job_role_id"))
    Set<JobRole> job_roles;

    public User() {
    }

    public User(int id, String name, String lastname, String email, String email_verified_at, String password, String avatar, String remember_token, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.password = password;
        this.avatar = avatar;
        this.remember_token = remember_token;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
    public User(int id, String name, String lastname, String email, String email_verified_at, String password, String avatar, String remember_token, String created_at, String updated_at, Set<JobRole> job_roles) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.password = password;
        this.avatar = avatar;
        this.remember_token = remember_token;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.job_roles = job_roles;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Set<JobRole> getJobRoles() {
        return job_roles;
    }

    public void setJobRoles(Set<JobRole> job_roles) {
        this.job_roles = job_roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(id, user.getId()) &&
        Objects.equals(name, user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + "]";
    }

}