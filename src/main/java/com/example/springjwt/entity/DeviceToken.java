package com.example.springjwt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "device_token")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class DeviceToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "token_id")
    private long id;
    
    private String token;

}
