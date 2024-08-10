package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users", uniqueConstraints={
        @UniqueConstraint(name="UNIQUE_user_email", columnNames = "email")
})
public class User {
    @Id
    @SequenceGenerator(name="users_seq_gen", sequenceName="users_id_seq", allocationSize=1)
    @GeneratedValue(generator="users_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="fullName", nullable=false)
    private String fullName;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="profilePicture", nullable=true)
    private String profilePicture;
}
