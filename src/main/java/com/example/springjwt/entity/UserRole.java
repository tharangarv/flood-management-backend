package com.example.springjwt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(unique = true)
    private String userRoleName;

}
