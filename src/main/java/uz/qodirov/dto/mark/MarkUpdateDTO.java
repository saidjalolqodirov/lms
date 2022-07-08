package uz.qodirov.dto.mark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 11:32 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkUpdateDTO extends GenericDTO {
    private int mark;
}
