package com.tapprovisionnement.tricol.service;

import com.tapprovisionnement.tricol.dto.CommandeLigneDTO;
import com.tapprovisionnement.tricol.mapper.CommandeLigneMapper;
import com.tapprovisionnement.tricol.model.Commande;
import com.tapprovisionnement.tricol.model.CommandeLigne;
import com.tapprovisionnement.tricol.model.Produit;
import com.tapprovisionnement.tricol.repository.CommandeLigneRepository;
import com.tapprovisionnement.tricol.repository.CommandeRepository;
import com.tapprovisionnement.tricol.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandeLigneService {
    private final CommandeLigneRepository commandeLigneRepository;
    private final CommandeLigneMapper commandeLigneMapper;
    private final ProduitRepository produitRepository;
    private final CommandeRepository commandeRepository;

    //getAll ligneCommandes
    public Page<CommandeLigneDTO> getAll(int page,int nbrElement){
        Pageable pageable= PageRequest.of(page,nbrElement, Sort.by("id").ascending());
        Page<CommandeLigne> commandeLignes=commandeLigneRepository.findAll(pageable);
        return commandeLignes.map(commandeLigneMapper::toDTO);
    }

    //get by id
    public CommandeLigneDTO getById(int id){
        CommandeLigne commandeLigne=commandeLigneRepository.findById(id).orElseThrow(()->new RuntimeException("commandeligneNotFound"));
        return commandeLigneMapper.toDTO(commandeLigne);
    }

    //save
    public CommandeLigneDTO createCommandeLigne(CommandeLigneDTO commandeLigneDTO){
        CommandeLigne commandeLigne=commandeLigneMapper.toEntiry(commandeLigneDTO);
        CommandeLigne commandeLigneSaved=commandeLigneRepository.save(commandeLigne);
        return commandeLigneMapper.toDTO(commandeLigneSaved);
    }

    //update
    public CommandeLigneDTO updateCommandeLigne(int id,CommandeLigneDTO commandeLigneDTO){
        CommandeLigne commandeLigne=commandeLigneRepository.findById(id).orElseThrow(()->new RuntimeException("commandeLigne not found"));
        Commande commande=commandeRepository.findById(commandeLigneDTO.getCommandeId()).orElseThrow(()->new RuntimeException("commande not found"));
        Produit produit=produitRepository.findById(commandeLigneDTO.getProduitId()).orElseThrow(()->new RuntimeException("product not found"));
        commandeLigne.setProduit(produit);
        commandeLigne.setCommande(commande);
        commandeLigne.setQuantite(commandeLigneDTO.getQuantite());
        commandeLigne.setPrixAchat(commandeLigneDTO.getPrixAchat());

        CommandeLigne commandeLigneSaved=commandeLigneRepository.save(commandeLigne);
        return commandeLigneMapper.toDTO(commandeLigneSaved);

    }


}
