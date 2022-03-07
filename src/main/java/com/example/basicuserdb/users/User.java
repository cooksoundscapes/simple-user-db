package com.example.basicuserdb.users;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.*;

@Entity
@Table(name = "users")
/**If @Table is left with no args, the default name would be "user", 
 * which is a reserved keyword, thus not creating it. It could warn us, though */
public class User {
    @Id @SequenceGenerator(
        name="user_sequence",
        sequenceName="user_sequence",
        allocationSize=1
    )
    @GeneratedValue(
         strategy=GenerationType.SEQUENCE,
         generator="user_sequence"
    )
    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birth;
    @Transient
    private Integer age;
    
    public User() {}

    public User(long id, String name, String email, String password, LocalDate birth) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setBirth(birth);
    }

    public User(String name, String email, String password, LocalDate birth) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setBirth(birth);
    }

    public Integer getAge() {
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {this.age = age;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Student{"+
                "id="+id +
                "name="+name +
                "email="+email +
                "password="+password +
                "birth="+birth +
                "}";
    }
}