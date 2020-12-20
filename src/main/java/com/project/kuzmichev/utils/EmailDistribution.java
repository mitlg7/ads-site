package com.project.kuzmichev.utils;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.service.ad.AdService;
import com.project.kuzmichev.service.email.EmailService;
import com.project.kuzmichev.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Component
public class EmailDistribution implements ApplicationRunner {
    @Autowired
    private EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    AdService adService;

    Runnable task = ()  ->{
        List<User> list = userService.getAllUsersForDistribution();
        for (User user: list){
            if(user.getSubscriptions()!=null){
                if(user.getSubscriptions().getLastSend()==null)
                    continue;

                Date differentDate = new Date(new Date().getTime() - user.getSubscriptions().getLastSend().getTime());
                if (differentDate.getTime() > 259200000){ // Трое суток
                    List<Ad> adList = adService.getFiveAdsByAdTypeAndByAdCategory(user.getSubscriptions().getAdType(), user.getSubscriptions().getAdCategory());
                    if(adList.isEmpty()){
                        adList = adService.getFiveAdsByAdCategory(user.getSubscriptions().getAdCategory());
                        if (adList.isEmpty()){
                            continue;
                        }
                    }
                    StringBuilder message = new StringBuilder();
                    message.append("Здравствуйте, " +user.getUsername() + "!<br>"+
                            "Возможно, Вам будут инетересны следующие объявления:\n");
                    adList.forEach(x -> message.append("<a href = 'https://alito64.herokuapp.com/ad/" + x.getId() + "'>"+ x.getName()+"</a> - "+x.getCost()+" руб.<br>"));

                    emailService.sendMessage(user.getEmail()
                            ,"Возмножно, Вам будет интресно...",
                            message.toString());
                    user.getSubscriptions().setLastSend(new Date());
                    userService.save(user);
                }
            }
        }
    };



    @Override
    public void run(ApplicationArguments args) throws Exception {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(task, 0, 4, TimeUnit.HOURS);

    }
}
