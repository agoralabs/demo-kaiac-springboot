package demo.kaiac.springboot.api.pojo;

import java.util.Objects;
import jakarta.persistence.*;



@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "priority")
    private String priority;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "scheduled_at")
    private String scheduled_at;
    @Column(name = "created_at")
    private String created_at;
    @Column(name = "updated_at")
    private String updated_at;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User responsible;

    public Task() {
    }

    public Task(int id, String name, String priority, String status, String description, String scheduled_at, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.scheduled_at = scheduled_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Task(int id, String name, String priority, String status, String description, String scheduled_at, String created_at, String updated_at, User responsible) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.scheduled_at = scheduled_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.responsible = responsible;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScheduled_at() {
        return scheduled_at;
    }

    public void setScheduled_at(String scheduled_at) {
        this.scheduled_at = scheduled_at;
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

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;
        return Objects.equals(id, task.getId()) &&
        Objects.equals(name, task.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", status=" + status + "]";
    }

}