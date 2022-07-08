package uz.qodirov.dto.journal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:17 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalCreateDTO implements BaseDTO {
    private String name;
    private Long groupId;
}
