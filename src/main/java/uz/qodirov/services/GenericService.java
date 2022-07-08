package uz.qodirov.services;

import org.springframework.http.ResponseEntity;
import uz.qodirov.dto.GenericDTO;
import uz.qodirov.dto.response.DataDTO;

import java.io.Serializable;
import java.util.List;


/**
 * @param <D> -> Dto
 * @param <K> -> class that defines the primary key for your pojo class
 */
public interface GenericService<
        D extends GenericDTO,
        K extends Serializable> extends BaseService {

    ResponseEntity<DataDTO<List<D>>> getAll();

    ResponseEntity<DataDTO<D>> get(K id);

}
