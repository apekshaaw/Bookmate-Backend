package com.example.demo.controller;

import com.example.demo.pojo.FavouritePojo;
import com.example.demo.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Favourite")
@RequiredArgsConstructor
public class FavouriteController {

    private final FavouriteService favouriteService;

    @PostMapping("/add")
    public void addFavourite(@RequestBody FavouritePojo favouritePojo) {
        this.favouriteService.addFavourite(favouritePojo);
    }

    @GetMapping("/getByUserId/{userId}")
    public List<FavouritePojo> getFavouritesByUserId(@PathVariable Integer userId) {
        return this.favouriteService.getFavouritesByUserId(userId);
    }

    @DeleteMapping("/remove/{id}")
    public void removeFavourite(@PathVariable Integer id) {
        this.favouriteService.removeFavourite(id);
    }
}
