package be.ucll.controller;

import be.ucll.model.Cursus;
import be.ucll.model.Inschrijving;
import be.ucll.model.Student;
import be.ucll.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final StudentService studentService;

    @Autowired

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAllStudentsOrByName(@RequestParam(value = "name", required = false, defaultValue = "none") String name){
        return studentService.findAllStudentsOrByName(name);
    }

    @GetMapping("/search/course/{course}")
    public List<Student> findStudentsByCourse(@PathVariable String course){
        return studentService.findByCourse(course);
    }

    @GetMapping("/search/admission/{admission}")
    public List<Student> findStudentsByAdmission(@PathVariable String admission){
        return studentService.findByAdmission(admission);
    }

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PostMapping
    public Inschrijving addInschrijving(@Valid @RequestBody Inschrijving inschrijving){
        return studentService.addInschrijving(inschrijving);
    }

    @PostMapping
    public Cursus addCursus(@Valid @RequestBody Cursus cursus){
        return studentService.addCursus(cursus);
    }



}
