package com.tapprovisionnement.tricol.dto;

import com.tapprovisionnement.tricol.enums.StatutCommande;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDTO {
    private int id;
    private LocalDateTime date;
    private StatutCommande statut;
    private double montantTotal;
    private int fournisseurId;
}
