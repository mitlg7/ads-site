package com.project.kuzmichev.model.repository;

import com.project.kuzmichev.model.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response,Integer> {
    List<Response> findByRead(boolean read);
    List<Response> findByIdAd(int adId);
    List<Response> findByUsernameOrderByDateDesc(String username);

}
