package uz.qodirov.dto.group;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 7:55 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreateDTO implements BaseDTO {
    @NotBlank
    private String name;
    @NotNull
    private Long facultyId;
    @NotNull
    private Date year;
}
