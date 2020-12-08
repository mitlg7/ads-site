package com.project.kuzmichev.service.ad;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdStatus;
import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.repository.AdRepository;
import com.project.kuzmichev.model.repository.UserRepository;
import com.project.kuzmichev.service.email.EmailService;
import com.project.kuzmichev.utils.AdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.project.kuzmichev.model.repository.FilmFilterSpecification.*;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll(byAdStatus(AdStatus.ACTIVELY));
    }

    @Override
    public List<Ad> getAllAdsByAdmin() {
        List<Ad> list = adRepository.findAll();
        return list;
    }

    @Override
    public List<Ad> getAllAdsByFilter(AdFilter filter) {
        List <Ad> list =adRepository.findAll(byAdCategory(filter.getAdCategory())
                .and(byAdType(filter.getAdType()))
                .and(byAdStatus(AdStatus.ACTIVELY))
                .and(greaterThanMinCost(filter.getMinCost()))
                .and(lessThanMaxCost(filter.getMaxCost())));
        return list;
    }

    @Override
    public List<Ad> getAllAdsByUsername(String username) {
        return adRepository.findByUsername(username);
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
        return adRepository.findByNameContainingIgnoreCase(request).stream()
                .filter(x -> x.getAdStatus().equals(AdStatus.ACTIVELY))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ad> getAdById(int id) {
        return adRepository.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public boolean createAd(Ad ad) {
        ad.setDate(new Date());
        ad.setAdStatus(AdStatus.CREATED);
        adRepository.save(ad);
        return true;
    }

    @Override
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public boolean deleteAd(int id) {
        adRepository.deleteById(id);
        return true;

    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public boolean setStatusAd(Ad ad, AdStatus adStatus) {
        Ad a = adRepository.findById(ad.getId()).get();
        a.setAdStatus(adStatus);
        adRepository.save(a);
        User user = userRepository.findByUsername(a.getUsername());
        emailService.sendSimpleMessage(user.getEmail(),
                "Статус вашего объявление изменился",
                "Здравствуйте, " + user.getFirstName() + " " + user.getSecondName() + ".\n"+
                "Статус вашего объявления <"+ a.getName() +"> изменился на " + adStatus.toString() +".\n"+
                "Следите за статусом вашего объявления в личном кабинете.");
        return true;

    }

    @Override
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public boolean updateAd(Ad ad) {
        adRepository.save(ad);
        return true;

    }
}
