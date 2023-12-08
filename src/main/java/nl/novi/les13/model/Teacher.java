package nl.novi.les13.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name", length = 128)
    private String firstName;

    @Column(name="last_name", length = 128)
    private String lastName;

    private LocalDate dob;

    @ManyToMany(mappedBy = "teachers")
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<Course> getCourses() {
        return courses;
    }
}
