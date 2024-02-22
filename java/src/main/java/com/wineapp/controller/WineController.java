package com.wineapp.controller;

import com.wineapp.dao.WineDao;
import com.wineapp.model.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/savedWines")
public class WineController {

    @Autowired
    private WineDao wineDao;

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public List<Wine> getSavedWines(@PathVariable int userId) {
        return wineDao.getSavedWines(userId);
    }

    @RequestMapping(path = "/addWine", method = RequestMethod.POST)
    public Wine saveWine(@RequestBody Wine wine, @RequestParam int userId) {
        return wineDao.saveWine(wine, userId);
    }



}
