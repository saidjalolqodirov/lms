package uz.qodirov.services;

import org.springframework.http.ResponseEntity;
import uz.qodirov.dto.BaseDTO;
import uz.qodirov.dto.GenericDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.entity.BaseEntity;

import java.io.Serializable;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 * @param <K>  -> class that defines the primary key for your pojo class
 */
public interface GenericCrudService<
        E extends BaseEntity,
        D extends GenericDTO,
        CD extends BaseDTO,
        UD extends GenericDTO,
        K extends Serializable> extends GenericService<D, K> {

    ResponseEntity<DataDTO<D>> create(CD createDto);

    ResponseEntity<DataDTO<Void>> delete(K id);

    ResponseEntity<DataDTO<D>> update(UD updateDto);

}
