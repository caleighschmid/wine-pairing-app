package com.wineapp.controller;

import com.wineapp.dao.WineDao;
import com.wineapp.model.Wine;
import com.wineapp.model.User;
import com.wineapp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/wines")
public class WineController {

    @Autowired
    private WineDao wineDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/saved", method = RequestMethod.GET)
    public List<Wine> getSavedWines(Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        return wineDao.getSavedWines(user.getId());
    }

    @RequestMapping(path = "/addWine", method = RequestMethod.POST)
    public Wine saveWine(@RequestBody Wine wine, Principal principal) {
        System.out.println(principal);
        User user = userDao.getUserByUsername((principal.getName()));
        return wineDao.saveWine(wine, user.getId());
    }



}
