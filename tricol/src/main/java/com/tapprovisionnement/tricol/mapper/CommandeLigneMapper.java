package com.tapprovisionnement.tricol.mapper;


import com.tapprovisionnement.tricol.dto.CommandeLigneDTO;
import com.tapprovisionnement.tricol.model.CommandeLigne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommandeLigneMapper {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "commande.id", target = "commandeId")
    CommandeLigneDTO toDTO(CommandeLigne commandeLigne);

    @Mapping(target = "produit", ignore = true)
    @Mapping(target = "commande", ignore = true)
    CommandeLigne toEntiry(CommandeLigneDTO commandeLigneDTO);
}
