package com.wineapp.controller;

import java.util.List;

import com.wineapp.model.WineRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wineapp.services.SpoontacularService;

@RestController
public class SpoontacularController {

    @Autowired
    SpoontacularService spoontacularService;
    @CrossOrigin
    @RequestMapping(path="/getPairing", method=RequestMethod.GET)
    public WineRecommendation test(@RequestParam String query) {

        return spoontacularService.getWinePairing(query);

    }

}