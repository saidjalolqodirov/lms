package uz.qodirov.services.mark;

import uz.qodirov.dto.mark.MarkCreateDTO;
import uz.qodirov.dto.mark.MarkDTO;
import uz.qodirov.dto.mark.MarkUpdateDTO;
import uz.qodirov.entity.Mark;
import uz.qodirov.services.GenericCrudService;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 11:30 PM
 */
public interface MarkService extends GenericCrudService<Mark, MarkDTO, MarkCreateDTO, MarkUpdateDTO, Long> {
}
