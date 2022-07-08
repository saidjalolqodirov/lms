package uz.qodirov.mapper;

import uz.qodirov.dto.BaseDTO;
import uz.qodirov.dto.GenericDTO;
import uz.qodirov.entity.BaseEntity;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 4:55 PM
 */
public interface GenericMapper<
        E extends BaseEntity,
        D extends BaseDTO,
        CD extends BaseDTO,
        UD extends GenericDTO, L extends Number> extends BaseMapper {

    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD cd);

    E fromUpdateDto(UD d);
}
