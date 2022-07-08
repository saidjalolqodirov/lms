package uz.qodirov.dto.mark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 11:31 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkCreateDTO implements BaseDTO {
    @Min(0)
    @Max(5)
    private int mark;
    @NotNull
    private Long studentId;
    @NotNull
    private Long assigmentId;

}
