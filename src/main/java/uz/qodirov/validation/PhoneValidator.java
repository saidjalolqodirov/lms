package uz.qodirov.validation;

import org.springframework.beans.factory.annotation.Autowired;
import uz.qodirov.repository.StudentRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneValid, String> {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^[09]{16}$") && !studentRepository.existsByPhone(s);
    }
}
