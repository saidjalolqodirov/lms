package uz.qodirov.dto.univercity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;
import uz.qodirov.validation.UniqueName;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 12:30 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityCreateDTO implements BaseDTO {

    @NotBlank
    @UniqueName(message = "this university already exist")
    private String name;

    @NotBlank
    private String address;

    private Date openYear;
}
