package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.FournisseurDTO;
import com.tapprovisionnement.tricol.model.Fournisseur;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-14T11:42:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class FournisseurMapperImpl implements FournisseurMapper {

    @Override
    public FournisseurDTO toDTO(Fournisseur fournisseur) {
        if ( fournisseur == null ) {
            return null;
        }

        FournisseurDTO.FournisseurDTOBuilder fournisseurDTO = FournisseurDTO.builder();

        fournisseurDTO.id( fournisseur.getId() );
        fournisseurDTO.societe( fournisseur.getSociete() );
        fournisseurDTO.adresse( fournisseur.getAdresse() );
        fournisseurDTO.contact( fournisseur.getContact() );
        fournisseurDTO.email( fournisseur.getEmail() );
        fournisseurDTO.telephone( fournisseur.getTelephone() );
        fournisseurDTO.ville( fournisseur.getVille() );
        fournisseurDTO.ICE( fournisseur.getICE() );

        return fournisseurDTO.build();
    }

    @Override
    public Fournisseur toEntity(FournisseurDTO fournisseurDTO) {
        if ( fournisseurDTO == null ) {
            return null;
        }

        Fournisseur.FournisseurBuilder fournisseur = Fournisseur.builder();

        fournisseur.id( fournisseurDTO.getId() );
        fournisseur.societe( fournisseurDTO.getSociete() );
        fournisseur.adresse( fournisseurDTO.getAdresse() );
        fournisseur.contact( fournisseurDTO.getContact() );
        fournisseur.email( fournisseurDTO.getEmail() );
        fournisseur.telephone( fournisseurDTO.getTelephone() );
        fournisseur.ville( fournisseurDTO.getVille() );
        fournisseur.ICE( fournisseurDTO.getICE() );

        return fournisseur.build();
    }
}
