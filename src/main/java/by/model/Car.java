package by.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Table(name = "car")
public class Car {
    @Id
    private Long id;

    private String model;

    private Integer horsepower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerId")
    @JsonIgnore
    private Person person;
}
