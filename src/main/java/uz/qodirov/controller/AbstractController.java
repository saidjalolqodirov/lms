package uz.qodirov.controller;

import uz.qodirov.services.BaseService;

public class AbstractController<S extends BaseService> {

    public AbstractController(S service) {
        this.service = service;
    }

    protected S service;
    protected final String API = "/api";
    protected final String VERSION = "/v1";

    protected final String PATH = API + VERSION;

}
