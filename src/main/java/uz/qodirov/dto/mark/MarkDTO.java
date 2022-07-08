package uz.qodirov.dto.mark;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:35 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarkDTO extends GenericDTO {
    private int mark;
    private String assigmentName;
    private String date;
    private String studentName;
    private String groupName;
}