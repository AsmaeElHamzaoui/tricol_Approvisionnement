package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.CommandeDTO;
import com.tapprovisionnement.tricol.model.Commande;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandeMapper {
    CommandeDTO  toDTO(Commande commande);
    Commande toEntity(CommandeDTO commandeDTO);
}
