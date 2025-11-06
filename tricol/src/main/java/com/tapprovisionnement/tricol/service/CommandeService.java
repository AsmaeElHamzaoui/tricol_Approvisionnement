package com.tapprovisionnement.tricol.service;

import com.tapprovisionnement.tricol.dto.CommandeDTO;
import com.tapprovisionnement.tricol.enums.StatutCommande;
import com.tapprovisionnement.tricol.mapper.CommandeMapper;
import com.tapprovisionnement.tricol.model.Commande;
import com.tapprovisionnement.tricol.model.CommandeLigne;
import com.tapprovisionnement.tricol.model.Fournisseur;
import com.tapprovisionnement.tricol.model.Produit;
import com.tapprovisionnement.tricol.repository.CommandeLigneRepository;
import com.tapprovisionnement.tricol.repository.CommandeRepository;
import com.tapprovisionnement.tricol.repository.FournisseurRepository;
import com.tapprovisionnement.tricol.repository.ProduitRepository;
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
    private final FournisseurRepository fournisseurRepository;
    private final CommandeLigneRepository commandeLigneRepository;
    private final ProduitRepository produitRepository;

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

    //save
    public CommandeDTO createCommande(CommandeDTO commandeDTO){
        // Récupérer le fournisseur
        Fournisseur fournisseur = fournisseurRepository
                .findById(commandeDTO.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur introuvable"));
       Commande commande=commandeMapper.toEntity(commandeDTO);
       // Associer le fournisseur
        commande.setFournisseur(fournisseur);

       Commande saved=commandeRepository.save(commande);

        // Traiter livraison si le statut est LIVREE dès la création
        traiterLivraisonCommande(saved);

       return commandeMapper.toDTO(saved);
    }

    //update
    public CommandeDTO updateCommande(int id,CommandeDTO commandeDTO){
        Commande commande=commandeRepository.findById(id).orElseThrow(()->new RuntimeException("Commande not found"));
        Fournisseur fournisseur=fournisseurRepository.findById(commandeDTO.getFournisseurId()).orElseThrow(()->new RuntimeException("fournisseur not found"));
        commande.setDateCommande(commandeDTO.getDateCommande());
        commande.setStatut(commandeDTO.getStatut());
        commande.setMontantTotal(commandeDTO.getMontantTotal());
        commande.setFournisseur(fournisseur);
        Commande saved=commandeRepository.save(commande);

        // Traiter livraison si le statut devient LIVREE
        traiterLivraisonCommande(saved);

        return commandeMapper.toDTO(saved);
    }

   //delete
    public void deleteCommande(int id){
        commandeRepository.deleteById(id);
    }

    private void traiterLivraisonCommande(Commande commande) {
        if (commande.getStatut() != StatutCommande.LIVREE) {
            return; // Rien à faire si la commande n'est pas LIVREE
        }

        for (CommandeLigne ligne : commandeLigneRepository.findByCommande(commande)) {
            Produit produit = ligne.getProduit();
            int stockRestant = produit.getStockActuel() - ligne.getQuantite();

            if (stockRestant < 0) {
                throw new RuntimeException(
                        "Stock insuffisant pour le produit '" + produit.getNom() +
                                "'. Quantité demandée : " + ligne.getQuantite() +
                                ", stock actuel : " + produit.getStockActuel()
                );
            }

            produit.setStockActuel(stockRestant);
            produitRepository.save(produit);
        }
    }
}
