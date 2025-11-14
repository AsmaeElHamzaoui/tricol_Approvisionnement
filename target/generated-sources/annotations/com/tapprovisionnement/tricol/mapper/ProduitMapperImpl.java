package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.ProduitDTO;
import com.tapprovisionnement.tricol.model.Produit;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-14T11:42:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class ProduitMapperImpl implements ProduitMapper {

    @Override
    public ProduitDTO toDTO(Produit produit) {
        if ( produit == null ) {
            return null;
        }

        ProduitDTO.ProduitDTOBuilder produitDTO = ProduitDTO.builder();

        produitDTO.id( produit.getId() );
        produitDTO.nom( produit.getNom() );
        produitDTO.description( produit.getDescription() );
        produitDTO.prixUnitaire( produit.getPrixUnitaire() );
        produitDTO.categorie( produit.getCategorie() );
        produitDTO.stockActuel( produit.getStockActuel() );
        produitDTO.coutMoyenUnitaire( produit.getCoutMoyenUnitaire() );

        return produitDTO.build();
    }

    @Override
    public Produit toEntity(ProduitDTO produitDTO) {
        if ( produitDTO == null ) {
            return null;
        }

        Produit.ProduitBuilder produit = Produit.builder();

        produit.id( produitDTO.getId() );
        produit.nom( produitDTO.getNom() );
        produit.description( produitDTO.getDescription() );
        produit.prixUnitaire( produitDTO.getPrixUnitaire() );
        produit.categorie( produitDTO.getCategorie() );
        produit.stockActuel( produitDTO.getStockActuel() );
        produit.coutMoyenUnitaire( produitDTO.getCoutMoyenUnitaire() );

        return produit.build();
    }
}
