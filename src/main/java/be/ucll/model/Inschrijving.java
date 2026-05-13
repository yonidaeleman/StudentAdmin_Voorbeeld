package be.ucll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Inschrijving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "richting kan niet leeg zijn.")
    private String course;

    @NotBlank(message = "Inschrijvings datum kan niet leeg zijn.")
    private String date;

    @Min(value = 1, message = "minimum tijd van inschrijving is 1 jaar.")
    @Max(value = 6, message = "de maximum tijd is 6 jaar.")
    private int duration;

    @Min(value = 100, message = "het mininmum bedrag is 100.")
    @Max(value = 10000, message = "het maximum bedrag is 10000")
    private float bedrag;

    @ManyToMany
    @JoinTable(
            name = "inschrijving_cursus",
            joinColumns = @JoinColumn(name = "inschrijving_id"),
            inverseJoinColumns = @JoinColumn(name = "cursus_id")
    )
    private List<Cursus> cursussen;

    protected Inschrijving() {
    }

    public Inschrijving(String course, String date, int duration, float bedrag, List<Cursus> cursussen) {
        setCourse(course);
        setDate(date);
        setDuration(duration);
        setBedrag(bedrag);
        setCursussen(cursussen);
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<Cursus> getCursussen() {
        return cursussen;
    }

    public void setCursussen(List<Cursus> cursussen) {
        this.cursussen = cursussen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
