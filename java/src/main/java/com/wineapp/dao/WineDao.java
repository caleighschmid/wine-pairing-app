package com.wineapp.dao;

import com.wineapp.model.RegisterUserDto;
import com.wineapp.model.Wine;

import java.util.List;

public interface WineDao {
    List<Wine> getSavedWines(int userId);

    Wine getWineById(int id);

//    Wine getWineByTitle(String title);

    Wine saveWine(Wine wine, int userId);

}
