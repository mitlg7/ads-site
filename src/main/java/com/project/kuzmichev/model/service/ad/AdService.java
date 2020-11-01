package com.project.kuzmichev.model.service.ad;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdService {
    List<Ad> getAllAds();
    List<Ad> getAllAdsByUserId(int userId);
    List<Ad> getAllAdsByStatus(AdStatus adStatus);
    List<Ad> getAllAdsByDate(Date start, Date end);
    List<Ad> searchAdByName(String request);
    Optional<Ad> getAdById(int id);
    boolean createAd(Ad ad);
    boolean deleteAd(int id);
    boolean setStatusAd(Ad ad, AdStatus adStatus);
    boolean updateAd(Ad ad);
}