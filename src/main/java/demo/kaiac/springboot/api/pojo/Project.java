package demo.kaiac.springboot.api.pojo;

import java.util.Objects;
import jakarta.persistence.*;



@Entity
@Table(name = "tasks")
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "task_id")
    private int id;
    @Column(name = "task_name")
    private String title;
    @Column(name = "task_date")
    private String due;
    @Column(name = "task_color")
    private String status;
    @Column(name = "task_description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Member responsible;

    public Project() {
    }

    public Project(int id, String title, String due, String status, String description) {
        this.id = id;
        this.title = title;
        this.due = due;
        this.status = status;
        this.description = description;
    }

    public Project(int id, String title, String due, String status, String description, Member responsible) {
        this.id = id;
        this.title = title;
        this.due = due;
        this.status = status;
        this.description = description;
        this.responsible = responsible;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member getResponsible() {
        return responsible;
    }

    public void setResponsible(Member status) {
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

        Project project = (Project) o;
        return Objects.equals(id, project.getId()) &&
        Objects.equals(title, project.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", title=" + title + ", status=" + status + "]";
    }

}