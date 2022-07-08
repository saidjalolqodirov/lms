package uz.qodirov.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DataDTO<T> implements Serializable {
    protected T data;
    protected AppErrorDTO error;
    protected boolean success;

    public DataDTO(boolean success) {
        this.success = success;
    }

    public DataDTO(T data) {
        this.data = data;
        this.success = true;
    }

    public DataDTO(AppErrorDTO error) {
        this.error = error;
        this.success = false;
    }
}
