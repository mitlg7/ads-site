package com.project.kuzmichev.web.controller;


import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdStatus;
import com.project.kuzmichev.service.ad.AdService;
import com.project.kuzmichev.service.ad.AdServiceImpl;
import com.project.kuzmichev.service.email.EmailServiceImpl;
import com.project.kuzmichev.utils.AdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/item")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public Optional<Ad> viewAd(@PathVariable("id") int id){
        return adService.getAdById(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Ad> views(AdFilter adFilter){
        if (adFilter.isEmpty())
            return adService.getAllAds();
        else
            return adService.getAllAdsByFilter(adFilter);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ResponseEntity createAd(@Valid @RequestBody Ad ad, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        if(adService.createAd(ad))
            return ResponseEntity.ok().body("Объявление успешно создано");
        else
            return ResponseEntity.badRequest().body("Не удалось создать объявление");
    }

    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ResponseEntity deleteAd(@PathVariable("id") int id){
        if( adService.deleteAd(id))
            return ResponseEntity.ok().body("Объявление успешно удалено");
        else
            return ResponseEntity.badRequest().body("Не удалось удалить объявление");
    }

    @PutMapping("/{id:\\d+}")
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public ResponseEntity updateAd(@PathVariable("id") int id,@Valid @RequestParam  Ad ad, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        if( adService.updateAd(ad))
            return ResponseEntity.ok().body("Объявление успешно обновлено");
        else
            return ResponseEntity.badRequest().body("Не удалось обновить объявление");
    }

    @GetMapping("/search")
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public List<Ad> searchByName(@RequestParam("name") String name){
        return adService.searchAdByName(name);
    }

    @PutMapping("/set/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity setStatus(@RequestBody HashMap<String,String> map){
        Ad ad = adService.getAdById(Integer.valueOf(map.get("adId"))).get();
        ad.setAdStatus(AdStatus.valueOf(map.get("adStatus")));
        adService.updateAd(ad);
        return ResponseEntity.ok().body("ok");
    }

}
