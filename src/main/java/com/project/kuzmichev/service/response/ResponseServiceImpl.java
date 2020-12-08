package com.project.kuzmichev.service.response;

import com.project.kuzmichev.model.domain.Response;
import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdStatus;
import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.repository.AdRepository;
import com.project.kuzmichev.model.repository.ResponseRepository;
import com.project.kuzmichev.model.repository.UserRepository;
import com.project.kuzmichev.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseServiceImpl implements ResponseService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public List<Response> getAll() {
        return responseRepository.findAll();
    }

    @Override
    public List<Response> getAllForUsername(String username) { //FIXME
        List<Response> list = new ArrayList<>();
        for(Ad ad: adRepository.findByUsername(username)){
            list.addAll(responseRepository.findByIdAd(ad.getId()));
        }
        return list;
    }

    @Override
    public List<Response> getAllForUsernameAndNew(String username){
        List<Response> list = getAllForUsername(username).stream()
                .filter( (x) -> !x.isRead())
                .sorted(Comparator.comparing(Response::getDate))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Response> getAllByUsername(String username) {
        return responseRepository.findByUsernameOrderByDateDesc(username);
    }

    @Override
    public List<Response> getAllByAdId(int adId) {
        return responseRepository.findByIdAd(adId);
    }

    @Override
    public boolean createResponse(Response response) {
        Ad ad = adRepository.findById(response.getIdAd()).orElse(null);
        if(ad != null)
            if(!ad.getAdStatus().equals(AdStatus.ACTIVELY))
                return false;
        response.setDate(new Date());
        response.setRead(false);
        responseRepository.save(response);
        return true;
    }

    @Override
    public boolean deleteResponse(int id) {
        Response response = responseRepository.findById(id).get();
        if(response!=null) {
            responseRepository.delete(response);
            return true;
        }
        return false;
    }

    @Override
    public boolean readResponse(int id) {
        Response response = responseRepository.findById(id).get();
        response.setRead(true);
        responseRepository.save(response);
        Ad ad = adRepository.findById(response.getIdAd()).get();
        User user = userRepository.findByUsername(ad.getUsername());
        emailService.sendSimpleMessage(user.getEmail(),
                "У вас новый отклик",
                "Здравствуйте, "+ user.getFirstName() + " " +user.getSecondName() + "\n"+
                "На ваше объявление < "+ ad.getName()+" > появился новый отклик\n" +
                "Пользователь:" + response.getUsername()+"\n" +
                "Телефон для связи: " + response.getPhone() + "\n" +
                "Сообщение: " + response.getMessage() + "\n" +
                "Переходите в личный кабинет для отслеживания откликов на ваши объявления.");
        return false;
    }


}
