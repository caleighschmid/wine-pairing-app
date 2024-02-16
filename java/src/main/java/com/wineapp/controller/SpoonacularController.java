package com.wineapp.controller;

import java.util.List;

import com.wineapp.model.WineRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wineapp.services.SpoonacularService;

@RestController
public class SpoonacularController {

    @Autowired
    SpoonacularService spoonacularService;
    @CrossOrigin
    @RequestMapping(path="/getPairing", method=RequestMethod.GET)
    public WineRecommendation test(@RequestParam String query) {

        return spoonacularService.getWinePairing(query);

    }

}