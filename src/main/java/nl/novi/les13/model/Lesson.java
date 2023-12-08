package nl.novi.les13.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String topic;

    // Les hoort bij 1 course
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

//    public Long courseId; // Niet in Lesson.java, maar in LessonDto omdat daar de input komt


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
