package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.MouvementStockDTO;
import com.tapprovisionnement.tricol.model.MouvementStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MouvementStockMapper {

    @Mapping(source="commande.id",target = "commandeId")
    MouvementStockDTO toDTO(MouvementStock mouvementStock);

    @Mapping(target="commande",ignore = true)
    MouvementStock toEntity(MouvementStockDTO mouvementStockDTO);
}
