package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.CommandeDTO;
import com.tapprovisionnement.tricol.model.Commande;
import com.tapprovisionnement.tricol.model.Fournisseur;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-14T11:42:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class CommandeMapperImpl implements CommandeMapper {

    @Override
    public CommandeDTO toDTO(Commande commande) {
        if ( commande == null ) {
            return null;
        }

        CommandeDTO.CommandeDTOBuilder commandeDTO = CommandeDTO.builder();

        commandeDTO.fournisseurId( commandeFournisseurId( commande ) );
        commandeDTO.id( commande.getId() );
        commandeDTO.dateCommande( commande.getDateCommande() );
        commandeDTO.statut( commande.getStatut() );
        commandeDTO.montantTotal( commande.getMontantTotal() );

        return commandeDTO.build();
    }

    @Override
    public Commande toEntity(CommandeDTO commandeDTO) {
        if ( commandeDTO == null ) {
            return null;
        }

        Commande.CommandeBuilder commande = Commande.builder();

        commande.id( commandeDTO.getId() );
        commande.dateCommande( commandeDTO.getDateCommande() );
        commande.statut( commandeDTO.getStatut() );
        commande.montantTotal( commandeDTO.getMontantTotal() );

        return commande.build();
    }

    private int commandeFournisseurId(Commande commande) {
        if ( commande == null ) {
            return 0;
        }
        Fournisseur fournisseur = commande.getFournisseur();
        if ( fournisseur == null ) {
            return 0;
        }
        int id = fournisseur.getId();
        return id;
    }
}
