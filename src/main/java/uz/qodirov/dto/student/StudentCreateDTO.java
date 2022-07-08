package uz.qodirov.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qodirov.dto.BaseDTO;
import uz.qodirov.validation.PhoneValid;
import uz.qodirov.validation.UniqueName;

import javax.validation.constraints.NotBlank;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 5:52 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateDTO implements BaseDTO {
    @NotBlank
    @UniqueName
    private String username;
    private String firstName;
    private String lastName;
    @UniqueName(message = "email already exist")
    private String email;
    @PhoneValid
    private String phone;
    private String address;
    private Long groupId;
}
