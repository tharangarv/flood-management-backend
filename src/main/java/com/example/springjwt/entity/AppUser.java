package com.example.springjwt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

}
