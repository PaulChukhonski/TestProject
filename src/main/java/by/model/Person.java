package by.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "person")
public class Person {
    @Id
    @NotEmpty(message = "Id shouldn't be empty")
    private Long id;

    @NotEmpty(message = "Name shouldn't be empty")
    private String name;

    @NotEmpty(message = "Birthdate shouldn't be empty")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Past
    private Date birthdate;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Car> cars;
}
