package com.vaitilingom.projetbackend.controller;


import com.vaitilingom.projetbackend.models.User;
import com.vaitilingom.projetbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @PostMapping (path = "inscription")
    public void inscription(@RequestBody User user){
        log.info("inscription");
        this.userService.inscription(user);
    }

}
