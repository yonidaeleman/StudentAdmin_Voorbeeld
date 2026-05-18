package be.ucll.controller;

import be.ucll.model.Cursus;
import be.ucll.model.Inschrijving;
import be.ucll.model.Student;
import be.ucll.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/course")
    public List<Cursus> findAllCourses(){return studentService.findAllCourses();}

    @GetMapping("/admission")
    public List<Inschrijving> findAllAdmissions(){return studentService.findAllAdmissions();}

    @PostMapping("/add/students")
    public Student addStudent(@Valid @RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PostMapping("/add/inschrijving")
    public Inschrijving addInschrijving(@Valid @RequestBody Inschrijving inschrijving){
        return studentService.addInschrijving(inschrijving);
    }

    @PostMapping("/add/cursus")
    public Cursus addCursus(@Valid @RequestBody Cursus cursus){
        return studentService.addCursus(cursus);
    }

    @PutMapping("/change/student/{name}")
    public Student changeStudent(@PathVariable String name, @Valid @RequestBody Student student){
        return studentService.changeUser(student, name);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class})
    public Map<String, String> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }



}
