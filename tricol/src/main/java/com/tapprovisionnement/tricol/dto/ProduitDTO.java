package com.tapprovisionnement.tricol.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDTO {
    private int id;
    private String nom;
    private String description;
    private double prixUnitaire;
    private String categorie;
    private int stockActuel;
    private double coutMoyenUnitaire;
}
