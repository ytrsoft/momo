package com.ytrsoft.controller;

import com.ytrsoft.MomoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping(value = "/nearby", produces = "application/json")
    public String getNearbyPeoples() {
        return MomoService.nearbyPeoples();
    }

    @GetMapping(value = "/search/{id}", produces = "application/json")
    public String search(@PathVariable("id") String id) {
        return MomoService.search(id);
    }

}
