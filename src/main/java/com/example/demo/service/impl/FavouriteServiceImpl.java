package com.example.demo.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.entity.Favourite;
import com.example.demo.entity.User;
import com.example.demo.pojo.FavouritePojo;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void addFavourite(FavouritePojo favouritePojo) {
        Favourite favourite = new Favourite();
        User user = userRepository.findById(favouritePojo.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(favouritePojo.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        favourite.setUser(user);
        favourite.setBook(book);

        favouriteRepository.save(favourite);
    }

    @Override
    public List<FavouritePojo> getFavouritesByUserId(Integer userId) {
        return favouriteRepository.findAll().stream()
                .filter(favourite -> favourite.getUser().getId().equals(userId))
                .map(favourite -> {
                    FavouritePojo favouritePojo = new FavouritePojo();
                    favouritePojo.setId(favourite.getId());
                    favouritePojo.setUserId(favourite.getUser().getId());
                    favouritePojo.setBookId(favourite.getBook().getId());
                    return favouritePojo;
                }).collect(Collectors.toList());
    }

    @Override
    public void removeFavourite(Integer id) {
        favouriteRepository.deleteById(id);
    }
}
