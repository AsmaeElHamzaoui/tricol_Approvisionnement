package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.MouvementStockDTO;
import com.tapprovisionnement.tricol.model.MouvementStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MouvementStockMapper {

    MouvementStockDTO toDTO(MouvementStock mouvementStock);
    MouvementStock toEntity(MouvementStockDTO mouvementStockDTO);
}
