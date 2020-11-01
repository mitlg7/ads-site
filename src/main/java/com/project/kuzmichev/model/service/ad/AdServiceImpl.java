package com.project.kuzmichev.model.service.ad;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdStatus;
import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.repository.AdRepository;
import com.project.kuzmichev.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public List<Ad> getAllAdsByUserId(int userId) {
        return adRepository.findByUserId(userId);
    }

    @Override
    public List<Ad> getAllAdsByStatus(AdStatus adStatus) {
        return (List<Ad>) adRepository.findByAdStatus(adStatus);
    }

    @Override
    public List<Ad> getAllAdsByDate(Date start, Date end) {
        return adRepository.findByDateBetween(start, end);
       /* List<Ad> list = adRepository.findAll();
        return list.stream()
                .filter(p -> p.getDate().after(start))
                .filter(p -> p.getDate().before(end))
                .collect(Collectors.toList());*/

    }

    @Override
    public List<Ad> searchAdByName(String request) {
        return adRepository.findByNameContainingIgnoreCase(request);
    }

    @Override
    public Optional<Ad> getAdById(int id) {
        return adRepository.findById(id);
    }

    @Override
    public boolean createAd(Ad ad) {
        adRepository.save(ad);
        return true;
    }

    @Override
    public boolean deleteAd(int id) {

        return true;

    }

    @Override
    public boolean setStatusAd(Ad ad, AdStatus adStatus) {
        Ad a = adRepository.findById(ad.getId()).get();
        a.setAdStatus(adStatus);
        adRepository.save(a);
        return true;

    }

    @Override
    public boolean updateAd(Ad ad) {
        adRepository.save(ad);
        return true;

    }
}
