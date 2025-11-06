package com.tapprovisionnement.tricol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int quantite;

    private int commandeId; // FK
}
