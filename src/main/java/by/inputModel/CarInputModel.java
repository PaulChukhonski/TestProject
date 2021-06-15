package by.inputModel;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CarInputModel {
    @Min(1)
    @Max(Long.MAX_VALUE)
    @NotNull
    private Long id;

    @Pattern(regexp = ".*-.*")
    @NotNull
    private String model;

    @Min(1)
    @Max(Integer.MAX_VALUE)
    @NotNull
    private Integer horsepower;

    @Min(1)
    @Max(Long.MAX_VALUE)
    @NotNull
    private Long ownerId;
}
