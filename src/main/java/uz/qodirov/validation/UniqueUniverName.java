package uz.qodirov.validation;

import org.springframework.beans.factory.annotation.Autowired;
import uz.qodirov.repository.StudentRepository;
import uz.qodirov.repository.UniversityRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 5:16 PM
 */
public class UniqueUniverName implements ConstraintValidator<UniqueName, String> {

    @Autowired
    UniversityRepository repository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !(repository.existsByName(s) || studentRepository.existsByEmail(s) || studentRepository.existsByUsername(s));
    }
}
