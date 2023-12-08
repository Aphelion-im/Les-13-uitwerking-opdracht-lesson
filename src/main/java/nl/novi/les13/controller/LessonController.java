package nl.novi.les13.controller;

import nl.novi.les13.dto.LessonDto;
import nl.novi.les13.model.Course;
import nl.novi.les13.model.Lesson;
import nl.novi.les13.repository.CourseRepository;
import nl.novi.les13.repository.LessonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LessonController {

    private final CourseRepository courseRepository;

    private final LessonRepository lessonRepository;

    public LessonController(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @PostMapping("/lessons")
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lessonDto) {
        Lesson lesson = new Lesson();
        lesson.setTopic(lessonDto.topic);

        Optional<Course> optionalCourse = courseRepository.findById(lessonDto.courseId); // Controle id bij de andere

        if (optionalCourse.isPresent()) {
            lesson.setCourse(optionalCourse.get());

            Lesson savedLesson = lessonRepository.save(lesson);

            LessonDto savedLessonDto = new LessonDto();

            savedLessonDto.id = savedLesson.getId();
            savedLessonDto.topic = savedLesson.getTopic();
            savedLessonDto.courseId = savedLesson.getCourse().getId();

            return new ResponseEntity<>(savedLessonDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
