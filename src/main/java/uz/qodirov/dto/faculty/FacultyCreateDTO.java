package uz.qodirov.dto.faculty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 6:59 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyCreateDTO implements BaseDTO {
    @NotBlank
    private String name;
    @NotNull
    private Long universityId;
    @NotNull
    private Date openYear;
}
