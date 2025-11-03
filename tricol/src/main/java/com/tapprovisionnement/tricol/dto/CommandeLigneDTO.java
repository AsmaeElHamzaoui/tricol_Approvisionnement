package com.tapprovisionnement.tricol.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeLigneDTO {
    private int id;
    private int produitId;
    private int commandeId;
    private int quantite;
    private double prixAchat;
}
