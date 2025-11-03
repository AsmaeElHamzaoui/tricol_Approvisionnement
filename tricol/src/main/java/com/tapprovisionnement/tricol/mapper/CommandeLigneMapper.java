package com.tapprovisionnement.tricol.mapper;


import com.tapprovisionnement.tricol.dto.CommandeLigneDTO;
import com.tapprovisionnement.tricol.model.CommandeLigne;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandeLigneMapper {
    CommandeLigneDTO toDTO(CommandeLigne commandeLigne);
    CommandeLigne toEntiry(CommandeLigneDTO commandeLigneDTO);
}
