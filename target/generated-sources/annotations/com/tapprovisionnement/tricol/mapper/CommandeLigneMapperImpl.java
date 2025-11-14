package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.CommandeLigneDTO;
import com.tapprovisionnement.tricol.model.Commande;
import com.tapprovisionnement.tricol.model.CommandeLigne;
import com.tapprovisionnement.tricol.model.Produit;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-14T11:42:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class CommandeLigneMapperImpl implements CommandeLigneMapper {

    @Override
    public CommandeLigneDTO toDTO(CommandeLigne commandeLigne) {
        if ( commandeLigne == null ) {
            return null;
        }

        CommandeLigneDTO.CommandeLigneDTOBuilder commandeLigneDTO = CommandeLigneDTO.builder();

        commandeLigneDTO.produitId( commandeLigneProduitId( commandeLigne ) );
        commandeLigneDTO.commandeId( commandeLigneCommandeId( commandeLigne ) );
        commandeLigneDTO.id( commandeLigne.getId() );
        commandeLigneDTO.quantite( commandeLigne.getQuantite() );
        commandeLigneDTO.prixAchat( commandeLigne.getPrixAchat() );

        return commandeLigneDTO.build();
    }

    @Override
    public CommandeLigne toEntiry(CommandeLigneDTO commandeLigneDTO) {
        if ( commandeLigneDTO == null ) {
            return null;
        }

        CommandeLigne.CommandeLigneBuilder commandeLigne = CommandeLigne.builder();

        commandeLigne.id( commandeLigneDTO.getId() );
        commandeLigne.quantite( commandeLigneDTO.getQuantite() );
        commandeLigne.prixAchat( commandeLigneDTO.getPrixAchat() );

        return commandeLigne.build();
    }

    private int commandeLigneProduitId(CommandeLigne commandeLigne) {
        if ( commandeLigne == null ) {
            return 0;
        }
        Produit produit = commandeLigne.getProduit();
        if ( produit == null ) {
            return 0;
        }
        int id = produit.getId();
        return id;
    }

    private int commandeLigneCommandeId(CommandeLigne commandeLigne) {
        if ( commandeLigne == null ) {
            return 0;
        }
        Commande commande = commandeLigne.getCommande();
        if ( commande == null ) {
            return 0;
        }
        int id = commande.getId();
        return id;
    }
}
