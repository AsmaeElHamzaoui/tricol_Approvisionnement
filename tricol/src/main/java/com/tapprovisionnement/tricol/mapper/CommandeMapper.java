package com.tapprovisionnement.tricol.mapper;

import com.tapprovisionnement.tricol.dto.CommandeDTO;
import com.tapprovisionnement.tricol.model.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    CommandeDTO  toDTO(Commande commande);

    @Mapping(target = "fournisseur", ignore = true)
    Commande toEntity(CommandeDTO commandeDTO);
}
