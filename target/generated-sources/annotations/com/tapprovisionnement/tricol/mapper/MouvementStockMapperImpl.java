package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.MouvementStockDTO;
import com.tapprovisionnement.tricol.model.Commande;
import com.tapprovisionnement.tricol.model.MouvementStock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-14T11:42:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class MouvementStockMapperImpl implements MouvementStockMapper {

    @Override
    public MouvementStockDTO toDTO(MouvementStock mouvementStock) {
        if ( mouvementStock == null ) {
            return null;
        }

        MouvementStockDTO.MouvementStockDTOBuilder mouvementStockDTO = MouvementStockDTO.builder();

        mouvementStockDTO.commandeId( mouvementStockCommandeId( mouvementStock ) );
        mouvementStockDTO.id( mouvementStock.getId() );
        if ( mouvementStock.getDateMouvement() != null ) {
            mouvementStockDTO.dateMouvement( DateTimeFormatter.ISO_LOCAL_DATE.format( mouvementStock.getDateMouvement() ) );
        }
        mouvementStockDTO.typeMouvement( mouvementStock.getTypeMouvement() );
        mouvementStockDTO.quantite( mouvementStock.getQuantite() );

        return mouvementStockDTO.build();
    }

    @Override
    public MouvementStock toEntity(MouvementStockDTO mouvementStockDTO) {
        if ( mouvementStockDTO == null ) {
            return null;
        }

        MouvementStock.MouvementStockBuilder mouvementStock = MouvementStock.builder();

        mouvementStock.id( mouvementStockDTO.getId() );
        if ( mouvementStockDTO.getDateMouvement() != null ) {
            mouvementStock.dateMouvement( LocalDate.parse( mouvementStockDTO.getDateMouvement() ) );
        }
        mouvementStock.typeMouvement( mouvementStockDTO.getTypeMouvement() );
        mouvementStock.quantite( mouvementStockDTO.getQuantite() );

        return mouvementStock.build();
    }

    private int mouvementStockCommandeId(MouvementStock mouvementStock) {
        if ( mouvementStock == null ) {
            return 0;
        }
        Commande commande = mouvementStock.getCommande();
        if ( commande == null ) {
            return 0;
        }
        int id = commande.getId();
        return id;
    }
}
