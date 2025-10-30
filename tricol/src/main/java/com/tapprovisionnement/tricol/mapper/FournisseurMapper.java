package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.FournisseurDTO;
import com.tapprovisionnement.tricol.model.Fournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FournisseurMapper {

    FournisseurMapper INSTANCE = Mappers.getMapper(FournisseurMapper.class);

    FournisseurDTO toDTO(Fournisseur fournisseur);

    Fournisseur toEntity(FournisseurDTO fournisseurDTO);
}
