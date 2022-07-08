package uz.qodirov.services.assigment;

import uz.qodirov.dto.assigment.AssigmentCreateDTO;
import uz.qodirov.dto.assigment.AssigmentDTO;
import uz.qodirov.dto.assigment.AssigmentUpdateDTO;
import uz.qodirov.entity.Assignment;
import uz.qodirov.services.GenericCrudService;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/8/2022 12:29 AM
 */
public interface AssigmentService extends GenericCrudService<Assignment, AssigmentDTO, AssigmentCreateDTO, AssigmentUpdateDTO, Long> {
}
