package uz.qodirov.validation.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import uz.qodirov.repository.FacultyRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 7:18 PM
 */
public class FacultyValidName implements ConstraintValidator<ValidName, String> {
    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !facultyRepository.existsByName(s);
    }
}
