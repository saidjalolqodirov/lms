package uz.qodirov.dto.assigment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/8/2022 12:30 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssigmentCreateDTO implements BaseDTO {
    private String name;
    private String description;
    private Long subjectId;
}
