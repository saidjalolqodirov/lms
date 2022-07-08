package uz.qodirov.dto.subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.GenericDTO;

import java.util.Set;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 1:09 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDTO extends GenericDTO {
    private String name;

    private String description;

    private Set<String> groupsName;
}
