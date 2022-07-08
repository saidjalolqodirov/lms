package uz.qodirov.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 1:09 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectUpdateDTO extends GenericDTO {
    private String name;
    private String description;

}
