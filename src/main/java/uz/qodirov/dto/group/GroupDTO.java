package uz.qodirov.dto.group;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

import java.sql.Date;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 7:55 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDTO extends GenericDTO {
    private String name;
    private String facultyName;
    private int studentCount;
    private Date year;
}
