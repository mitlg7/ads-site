package com.project.kuzmichev.model.repository;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Integer>, JpaSpecificationExecutor<Ad> {
    List<Ad> findByUsername(String username);
    List<Ad> findByNameContainingIgnoreCase(String name);
    List<Ad> findByDateBetween(Date start,Date end);
    Ad deleteById(int id);
    Optional<Ad> findById(int id);
    Ad findByAdStatus(AdStatus adStatus);

}
