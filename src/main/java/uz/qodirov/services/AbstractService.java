package uz.qodirov.services;

import uz.qodirov.mapper.BaseMapper;
import uz.qodirov.repository.BaseRepository;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 4:36 PM
 */
public abstract class AbstractService<R extends BaseRepository, M extends BaseMapper> implements BaseService {
    protected R repository;
    protected M mapper;

    protected AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
