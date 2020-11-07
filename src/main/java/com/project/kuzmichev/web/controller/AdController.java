package com.project.kuzmichev.web.controller;


import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.service.ad.AdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Ad> views(){
        return adServiceImpl.getAllAds();
    }

    @PostMapping
    @ResponseBody
    public boolean createAd(Ad ad){
        return adServiceImpl.createAd(ad);
    }

    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    public String deleteAd(@PathVariable("id") int id){
        adServiceImpl.deleteAd(id);
        return null;
    }

    @PutMapping("/{id:\\d+}")
    @ResponseBody
    public boolean updateAd(@PathVariable("id") int id, @RequestParam  Ad ad){
        return adServiceImpl.updateAd(ad);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Ad> searchByName(@RequestParam("name") String name){
        return adServiceImpl.searchAdByName(name);
    }
}
