package be.ucll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cursus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "cursus naam kan niet leeg zijn.")
    private String name;

    @Min(value = 1, message = "studieunten moeten tussen 1 en 10 liggen.")
    @Max(value = 10, message = "studieunten moeten tussen 1 en 10 liggen.")
    private int coursepoints;

    @NotBlank(message = "Cooridnator kan niet leeg zijn.")
    private String coordinator;

    protected Cursus() {
    }

    public Cursus(String name, int coursepoints, String coordinator) {
        setName(name);
        setCoursepoints(coursepoints);
        setCoordinator(coordinator);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoursepoints() {
        return coursepoints;
    }

    public void setCoursepoints(int coursepoints) {
        this.coursepoints = coursepoints;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }
}
