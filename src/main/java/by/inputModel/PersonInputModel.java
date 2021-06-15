package by.inputModel;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PersonInputModel {
    @Min(1)
    @Max(Long.MAX_VALUE)
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}")
    @NotNull
    private String birthdate;
}
