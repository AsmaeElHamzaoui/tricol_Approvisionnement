package com.tapprovisionnement.tricol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tapprovisionnement.tricol.enums.StatutCommande;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDTO {
    private int id;
    private LocalDateTime dateCommande;
    private StatutCommande statut;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double montantTotal;
    private int fournisseurId;
}
