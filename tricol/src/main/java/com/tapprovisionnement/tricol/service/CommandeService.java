package com.tapprovisionnement.tricol.service;

import com.tapprovisionnement.tricol.dto.CommandeDTO;
import com.tapprovisionnement.tricol.mapper.CommandeMapper;
import com.tapprovisionnement.tricol.model.Commande;
import com.tapprovisionnement.tricol.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;

    //getAll commandes with pagination sorting by id ascending
    public Page<CommandeDTO> getAllCommandes(int page,int nbrElement){
        Pageable pageable= PageRequest.of(page,nbrElement, Sort.by("id").ascending());
        Page<Commande> commandes=commandeRepository.findAll(pageable);
        return commandes.map(commandeMapper::toDTO);
    }

    //getByid
    public CommandeDTO getById(int id){
       Commande commande =commandeRepository.findById(id).orElseThrow(()->new RuntimeException("Commande not found"));
       return commandeMapper.toDTO(commande);
    }
}
