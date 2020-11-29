package com.project.kuzmichev.web.controller;

import com.project.kuzmichev.model.domain.Response;
import com.project.kuzmichev.service.response.ResponseService;
import com.project.kuzmichev.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/response")
@PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
public class ResponseController {

    @Autowired
    ResponseService responseService;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createResp(@Valid @RequestBody Response resp, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return  ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        if(responseService.createResponse(resp))
            return ResponseEntity.ok().body("Ok");
        else
            return ResponseEntity.badRequest().body("Что то пошло не так");
    }

    @DeleteMapping
    public ResponseEntity deleteResp(@RequestParam int id){
        if(responseService.deleteResponse(id))
            return ResponseEntity.ok("ok");
        else
            return ResponseEntity.badRequest().body("Что то пошло не так");
    }

    @GetMapping("/user")
    public List<Response> getForUsername(@RequestParam("username") String username){
        return responseService.getAllForUsername(username);
    }

    @GetMapping("/ad")
    public List<Response> getByAdId(@RequestParam("id") int id){
        return responseService.getAllByAdId(id);
    }

    @PostMapping("/read")
    public ResponseEntity read(@RequestBody int id){
        responseService.readResponse(id);
        return ResponseEntity.ok().body("Ok");
    }
    @PostMapping("/user/new")
    public List<Response> getForUsernameNew(@RequestBody String username){
        return responseService.getAllForUsernameAndNew(username);
    }
}
