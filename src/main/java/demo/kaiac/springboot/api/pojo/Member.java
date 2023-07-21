package demo.kaiac.springboot.api.pojo;

import java.util.Objects;
import jakarta.persistence.*;



@Entity
@Table(name = "users")
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_firstname")
    private String firstname;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "user_login")
    private String avatar;


    public Member() {
    }

    public Member(int id, String firstname, String surname, String avatar) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.avatar = avatar;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
        Objects.equals(firstname, member.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname);
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", firstname=" + firstname + ", surname=" + surname + "]";
    }

}