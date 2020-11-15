package com.project.kuzmichev.web.controller;


import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.service.ad.AdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdServiceImpl adServiceImpl;



    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public Optional<Ad> viewAd(@PathVariable("id") int id){
        return adServiceImpl.getAdById(id);
    }



    @GetMapping("/all")
    @ResponseBody
    @PreAuthorize("hasRole('CLIENT')")
    public List<Ad> views(){
        return adServiceImpl.getAllAds();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity createAd(Ad ad){
        if(adServiceImpl.createAd(ad))
            return ResponseEntity.ok().body("Объявление успешно создано");
        else
            return ResponseEntity.badRequest().body("Не удалось создать объявление");
    }

    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    public ResponseEntity deleteAd(@PathVariable("id") int id){
        if( adServiceImpl.deleteAd(id))
            return ResponseEntity.ok().body("Объявление успешно удалено");
        else
            return ResponseEntity.badRequest().body("Не удалось удалить объявление");

    }

    @PutMapping("/{id:\\d+}")
    @ResponseBody
    public ResponseEntity updateAd(@PathVariable("id") int id, @RequestParam  Ad ad){
        if( adServiceImpl.updateAd(ad))
            return ResponseEntity.ok().body("Объявление успешно обновлено");
        else
            return ResponseEntity.badRequest().body("Не удалось обновить объявление");
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Ad> searchByName(@RequestParam("name") String name){
        return adServiceImpl.searchAdByName(name);
    }
}
