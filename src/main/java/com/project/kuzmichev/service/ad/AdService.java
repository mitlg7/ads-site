package com.project.kuzmichev.service.ad;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdCategory;
import com.project.kuzmichev.model.domain.ad.AdStatus;
import com.project.kuzmichev.model.domain.ad.AdType;
import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.utils.AdFilter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdService {
    List<Ad> getAllAds();
    List<Ad> getAllAdsByAdmin();
    List<Ad> getAllAdsByFilter(AdFilter adFilter);
    List<Ad> getAllAdsByUsername(String username);
    List<Ad> getFiveAdsByAdCategory(AdCategory adCategory);
    List<Ad> getFiveAdsByAdType(AdType adType);

    List<Ad> getFiveAdsByAdTypeAndByAdCategory(AdType adType, AdCategory adCategory);
    List<Ad> getAllAdsByStatus(AdStatus adStatus);
    List<Ad> getAllAdsByDate(Date start, Date end);
    List<Ad> searchAdByName(String username, String request);
    Optional<Ad> getAdById(int id);
    boolean createAd(Ad ad);
    boolean deleteAd(int id);
    boolean setStatusAd(Ad ad, AdStatus adStatus);
    boolean updateAd(Ad ad);
}
