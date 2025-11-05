package com.tapprovisionnement.tricol.dto;

import com.tapprovisionnement.tricol.enums.TypeMouvement;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementStockDTO {

    private int id;
    private String dateMouvement;
    private TypeMouvement typeMouvement;
    private int quantite;

    private int ligneCommandeId; // FK
}
