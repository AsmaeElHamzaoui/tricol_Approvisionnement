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

    // GET all
    public Page<CommandeLigneDTO> getAll(int page, int nbrElement){
        Pageable pageable = PageRequest.of(page, nbrElement, Sort.by("id").ascending());
        return commandeLigneRepository.findAll(pageable)
                .map(commandeLigneMapper::toDTO);
    }

    // GET by id
    public CommandeLigneDTO getById(int id){
        CommandeLigne ligne = commandeLigneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CommandeLigne not found"));
        return commandeLigneMapper.toDTO(ligne);
    }

    // CREATE (POST) avec mapper
    public CommandeLigneDTO createCommandeLigne(CommandeLigneDTO dto){
        // Convertir le DTO en entity via mapper
        CommandeLigne ligne = commandeLigneMapper.toEntiry(dto);

        // Associer les entités existantes
        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        ligne.setProduit(produit);
        ligne.setCommande(commande);

        // Sauvegarder
        CommandeLigne saved = commandeLigneRepository.save(ligne);
        return commandeLigneMapper.toDTO(saved);
    }

    // UPDATE (PUT) avec mapper
    public CommandeLigneDTO updateCommandeLigne(int id, CommandeLigneDTO dto){
        CommandeLigne ligne = commandeLigneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CommandeLigne non trouvée"));

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        // Mettre à jour via mapper ou directement
        ligne.setQuantite(dto.getQuantite());
        ligne.setPrixAchat(dto.getPrixAchat());
        ligne.setProduit(produit);
        ligne.setCommande(commande);

        CommandeLigne saved = commandeLigneRepository.save(ligne);
        return commandeLigneMapper.toDTO(saved);
    }

    // DELETE
    public void deleteCommandeLigne(int id){
        commandeLigneRepository.deleteById(id);
    }
}

