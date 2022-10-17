package com.example.springjwt.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;
    private String longitude;
    private String latitude;
    @Column(unique = true)
    private String locationName;

}
