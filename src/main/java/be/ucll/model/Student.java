package be.ucll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty.")
    private String name;


    @Min(value = 0, message = "Age must be a positive integer between 0 and 101")
    @Max(value = 101, message = "Age must be a positive integer between 0 and 101")
    private int age;

    @NotBlank(message = "E-mail must be a valid email format")
    @Email(message = "E-mail must be a valid email format")
    private String email;

    @NotBlank(message = "Password must be at least 8 characters long")
    @Length(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @ManyToOne
    @JoinColumn(name = "inschrijving_id")
    private Inschrijving inschrijving;

    protected Student() {
    }

    public Student(String name, int age, String email, String password) {
        setName(name);
        setAge(age);
        setEmail(email);
        setPassword(password);
    }

    public Student(String name, int age, String email, String password, Inschrijving inschrijving) {
        setName(name);
        setAge(age);
        setEmail(email);
        setPassword(password);
        setInschrijving(inschrijving);
    }

    public Inschrijving getInschrijving() {
        return inschrijving;
    }

    public void setInschrijving(Inschrijving inschrijving) {
        if(this.age < 18){
            throw new RuntimeException("User must be at least 18 years old to have a profile.");
        }
        this.inschrijving = inschrijving;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
