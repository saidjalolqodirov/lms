package uz.qodirov.dto.univercity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

import java.sql.Date;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 12:31 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDTO extends GenericDTO {
    private String name;
    private String address;
    private Date openYear;
}
