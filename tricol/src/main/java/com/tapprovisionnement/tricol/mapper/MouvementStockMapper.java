package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.MouvementStockDTO;
import com.tapprovisionnement.tricol.model.MouvementStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MouvementStockMapper {

    @Mapping(source = "ligneCommande.id", target = "ligneCommandeId")
    MouvementStockDTO toDTO(MouvementStock mouvementStock);

    @Mapping(source = "ligneCommandeId", target = "ligneCommande.id")
    MouvementStock toEntity(MouvementStockDTO mouvementStockDTO);
}
