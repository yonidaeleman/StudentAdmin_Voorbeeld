package be.ucll.controller;

import be.ucll.model.Cursus;
import be.ucll.model.Richting;
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

    @GetMapping("/search/cursus/{cursus}")
    public List<Student> findStudentsByCourse(@PathVariable String cursus){
        return studentService.findByCourse(cursus);
    }

    @GetMapping("/search/richting/{richting}")
    public List<Student> findStudentsByRichting(@PathVariable String richting){
        return studentService.findByRichting(richting);
    }

    @GetMapping("/cursusen")
    public List<Cursus> findAllCourses(){return studentService.findAllCourses();}

    @GetMapping("/richtingen")
    public List<Richting> findAllAdmissions(){return studentService.findAllAdmissions();}

    @PostMapping("/add/student")
    public Student addStudent(@Valid @RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PostMapping("/add/richting")
    public Richting addrichting(@Valid @RequestBody Richting richting){
        return studentService.addRichting(richting);
    }

    @PostMapping("/add/cursus")
    public Cursus addCursus(@Valid @RequestBody Cursus cursus){
        return studentService.addCursus(cursus);
    }

    @PutMapping("/change/student/{name}")
    public Student changeStudent(@PathVariable String name, @Valid @RequestBody Student student){
        return studentService.changeStudent(student, name);
    }

    @PutMapping("/change/richting/{richting}")
    public Richting changerichting(@PathVariable String richting, @Valid @RequestBody Richting richtingToChange){
        return studentService.changeRichting(richting, richtingToChange);
    }

    @PutMapping("/change/cursus/{cursus}")
    public Cursus changeCursus(@PathVariable String cursus, @Valid @RequestBody Cursus cursusToChange){
        return studentService.changeCursus(cursus, cursusToChange);
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
