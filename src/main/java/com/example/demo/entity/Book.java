package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Book {
    @Id
    @SequenceGenerator(name="books_seq_gen", sequenceName="books_id_seq", allocationSize=1)
    @GeneratedValue(generator="books_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="description", nullable=false)
    private String description;

    @Column(name="image", nullable=true)
    private String image;

    @Column(name="pdf", nullable=true)
    private String pdf;
}
