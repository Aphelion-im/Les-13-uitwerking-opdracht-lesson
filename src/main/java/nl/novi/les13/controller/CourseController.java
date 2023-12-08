package nl.novi.les13.controller;

import nl.novi.les13.dto.CourseDto;
import nl.novi.les13.model.Course;
import nl.novi.les13.model.Lesson;
import nl.novi.les13.model.Teacher;
import nl.novi.les13.repository.CourseRepository;
import nl.novi.les13.repository.LessonRepository;
import nl.novi.les13.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    // ALWAYS USE SERVICES INSTEAD OF REPOSITORIES IN CONTROLLERS!!!
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.lessonRepository = lessonRepository;
    }


    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {

        // Normaliter horen de onderstaande 3 regels (Mapper) in de Service:
        Course course = new Course();
        course.setTitle(courseDto.title);
        course.setSp(courseDto.sp);

        // Zoiets als: Als docent bestaat, koppel deze dan toe aan de cursus
        // Het veld in CourseDto teacherIds:
        for (Long id : courseDto.teacherIds) {
            Optional<Teacher> ot = teacherRepository.findById(id);
            if (ot.isPresent()) { // Optional.isPresent(); - Vol of leeg: true/false.
                Teacher teacher = ot.get(); // Optional.get(); - Return the value that's inside the Optional
                course.getTeachers().add(teacher);
            }
        }

        for (Long id : courseDto.lessonIds) {
            Optional<Lesson> ol = lessonRepository.findById(id);
            if (ol.isPresent()) { // Optional.isPresent(); - Vol of leeg: true/false.
                Lesson lesson = ol.get(); // Optional.get(); - Return the value that's inside the Optional
                course.getLessons().add(lesson);
            }
        }

        courseRepository.save(course);
        courseDto.id = course.getId(); // Zonder deze regel geeft Postman null als id.
        return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
    }
}
