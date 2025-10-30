package com.tapprovisionnement.tricol.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fournisseurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String societe;

    private String adresse;

    private String contact;

    private String email;

    private String telephone;

    private String ville;

    @Column(name = "ice", unique = true)
    private String ICE;
}
