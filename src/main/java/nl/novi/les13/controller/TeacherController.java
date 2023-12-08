package nl.novi.les13.controller;

import jakarta.validation.Valid;
import nl.novi.les13.dto.TeacherDto;
import nl.novi.les13.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

//    @GetMapping
//    public ResponseEntity<List<Teacher>> getAllTeachers() {
//        return ResponseEntity.ok(teacherRepository.findAll());
//    }
//
//    @GetMapping("/after")
//    public ResponseEntity<List<Teacher>> getTeachersAfter(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(teacherRepository.findByDobAfter(date));
//    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) {

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }
        else {
            teacherDto = service.createTeacher(teacherDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + teacherDto.id).toUriString());

            return ResponseEntity.created(uri).body(teacherDto);
        }
    }
}
