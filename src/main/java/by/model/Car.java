package by.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "car")
public class Car {
    @Id
    @NotEmpty(message = "Id shouldn't be empty")
    private Long id;

    @NotEmpty(message = "Model shouldn't be empty")
    private String model;

    @Min(value = 0, message = "Horsepower should be greater than 0")
    @NotEmpty(message = "Horsepower shouldn't be empty")
    private Integer horsepower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerId")
    @NotEmpty(message = "Person shouldn't be empty")
    @JsonIgnore
    private Person person;
}
