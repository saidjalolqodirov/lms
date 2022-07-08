package uz.qodirov.dto.faculty;

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
public class FacultyUpdateDTO extends GenericDTO {
    private String name;

    private Date openYear;
}
