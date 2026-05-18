package be.ucll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Richting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "richting naam kan niet leeg zijn.")
    private String name;

    @Min(value = 1, message = "minimum tijd van inschrijving is 1 jaar.")
    @Max(value = 6, message = "de maximum tijd is 6 jaar.")
    private int duration;

    @Min(value = 100, message = "het mininmum bedrag is 100.")
    @Max(value = 10000, message = "het maximum bedrag is 10000")
    private float bedrag;

    @ManyToMany
    @JoinTable(
            name = "richting_cursus",
            joinColumns = @JoinColumn(name = "richting_id"),
            inverseJoinColumns = @JoinColumn(name = "cursus_id")
    )
    private List<Cursus> cursussen;

    protected Richting() {
    }

    public Richting(String name, int duration, float bedrag, List<Cursus> cursussen) {
        setName(name);
        setDuration(duration);
        setBedrag(bedrag);
        setCursussen(cursussen);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cursus> getCursussen() {
        return cursussen;
    }

    public void setCursussen(List<Cursus> cursussen) {
        this.cursussen = cursussen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getBedrag() {
        return bedrag;
    }

    public void setBedrag(float bedrag) {
        this.bedrag = bedrag;
    }
}
