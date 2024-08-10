package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="favourites")
public class Favourite {
    @Id
    @SequenceGenerator(name="favourites_seq_gen", sequenceName="favourites_id_seq", allocationSize=1)
    @GeneratedValue(generator="favourites_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;
}
