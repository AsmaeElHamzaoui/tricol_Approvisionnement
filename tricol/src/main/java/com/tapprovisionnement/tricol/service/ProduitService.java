package com.tapprovisionnement.tricol.service;

import com.tapprovisionnement.tricol.dto.ProduitDTO;
import com.tapprovisionnement.tricol.mapper.ProduitMapper;
import com.tapprovisionnement.tricol.model.Produit;
import com.tapprovisionnement.tricol.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;

    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProduitDTO getProduitById(int id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec id : " + id));
        return produitMapper.toDTO(produit);
    }

    public ProduitDTO createProduit(ProduitDTO produitDTO) {
        Produit produit = produitMapper.toEntity(produitDTO);
        Produit saved = produitRepository.save(produit);
        return produitMapper.toDTO(saved);
    }

    public ProduitDTO updateProduit(int id, ProduitDTO produitDTO) {
        Produit existing = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec id : " + id));

        // Mise à jour des champs
        existing.setNom(produitDTO.getNom());
        existing.setDescription(produitDTO.getDescription());
        existing.setPrixUnitaire(produitDTO.getPrixUnitaire());
        existing.setCategorie(produitDTO.getCategorie());
        existing.setStockActuel(produitDTO.getStockActuel());

        Produit updated = produitRepository.save(existing);
        return produitMapper.toDTO(updated);
    }

    public void deleteProduit(int id) {
        produitRepository.deleteById(id);
    }
}
