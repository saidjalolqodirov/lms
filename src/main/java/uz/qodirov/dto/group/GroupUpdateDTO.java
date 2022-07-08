package uz.qodirov.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 7:57 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupUpdateDTO extends GenericDTO {
    private String name;
}
