package uz.qodirov.dto.journal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;
import uz.qodirov.dto.GenericDTO;
import uz.qodirov.dto.subject.SubjectDTO;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:17 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JournalDTO extends GenericDTO {
    private String name;
    private String groupName;
    private List<SubjectDTO> subjectDTOS;
}
