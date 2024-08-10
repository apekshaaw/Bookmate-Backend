package com.example.demo.service;

import com.example.demo.pojo.FavouritePojo;

import java.util.List;

public interface FavouriteService {
    void addFavourite(FavouritePojo favouritePojo);
    List<FavouritePojo> getFavouritesByUserId(Integer userId);
    void removeFavourite(Integer id);
}
