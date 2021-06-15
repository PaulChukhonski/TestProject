package by.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "person")
public class Person {
    @Id
    private Long id;

    private String name;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthdate;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Car> cars;
}
