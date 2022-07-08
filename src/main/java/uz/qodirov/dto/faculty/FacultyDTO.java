package uz.qodirov.dto.faculty;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

import java.sql.Date;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 6:59 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacultyDTO extends GenericDTO {
    private String name;
    private String universityName;
    private Date openYear;
}
