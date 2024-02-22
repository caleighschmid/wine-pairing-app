package com.wineapp.dao;

import com.wineapp.exception.DaoException;
import com.wineapp.model.Wine;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcWineDao implements WineDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWineDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Wine> getSavedWines(int userId) {
        List<Wine> savedWines = new ArrayList<>();
        String sql = "SELECT * FROM saved_wines WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                Wine wine = mapRowToWine(results);
                savedWines.add(wine);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return savedWines;
    }

    @Override
    public Wine getWineById(int id) {
        Wine wine = null;
        String sql = "SELECT * FROM saved_wines WHERE wine_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                wine = mapRowToWine(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return wine;
    }

//    @Override
//    public Wine getWineByTitle(String title) {
//        return null;
//    }

    @Override
    public Wine saveWine(Wine wine, int userId) {
        Wine newWine = null;

        String sql = "INSERT INTO saved_wines (user_id, title, average_rating, description, image_url, link, price, rating_count, score) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING wine_id;";

        try {
            int newWineId = jdbcTemplate.queryForObject(sql, int.class, userId, wine.getTitle(), wine.getAverageRating(), wine.getDescription(), wine.getImageUrl(), wine.getLink(), wine.getPrice(), wine.getRatingCount(), wine.getScore());
            newWine = getWineById(newWineId);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newWine;
    }

    private Wine mapRowToWine(SqlRowSet rs){
        Wine wine = new Wine();
        wine.setId(rs.getInt("wine_id"));
        wine.setTitle(rs.getString("title"));
        wine.setAverageRating(rs.getDouble("average_rating"));
        wine.setDescription(rs.getString("description"));
        wine.setImageUrl(rs.getString("image_url"));
        wine.setLink(rs.getString("link"));
        wine.setPrice(rs.getString("price"));
        wine.setRatingCount(rs.getInt("rating_count"));
        wine.setScore(rs.getDouble("score"));

        return wine;
    }




}
