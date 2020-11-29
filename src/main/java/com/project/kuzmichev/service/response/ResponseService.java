package com.project.kuzmichev.service.response;

import com.project.kuzmichev.model.domain.Response;

import java.util.List;
import java.util.Optional;

public interface ResponseService {
    List<Response> getAll();
    List<Response> getAllForUsername(String username);
    List<Response> getAllByUsername(String username);
    List<Response> getAllByAdId(int id);
    boolean createResponse(Response response);
    boolean deleteResponse(int id);
    boolean readResponse(int id);
    List<Response> getAllForUsernameAndNew(String username);




}
