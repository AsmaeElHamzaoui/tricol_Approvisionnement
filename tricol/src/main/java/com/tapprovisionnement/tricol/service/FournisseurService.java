package com.tapprovisionnement.tricol.service;

import com.tapprovisionnement.tricol.dto.FournisseurDTO;
import com.tapprovisionnement.tricol.mapper.FournisseurMapper;
import com.tapprovisionnement.tricol.model.Fournisseur;
import com.tapprovisionnement.tricol.repository.FournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final FournisseurMapper fournisseurMapper;

    public List<FournisseurDTO> getAllFournisseurs() {
        return fournisseurRepository.findAll()
                .stream()
                .map(fournisseurMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FournisseurDTO getFournisseurById(int id) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec id : " + id));
        return fournisseurMapper.toDTO(fournisseur);
    }

    public FournisseurDTO createFournisseur(FournisseurDTO fournisseurDTO) {
        Fournisseur fournisseur = fournisseurMapper.toEntity(fournisseurDTO);
        Fournisseur saved = fournisseurRepository.save(fournisseur);
        return fournisseurMapper.toDTO(saved);
    }

    public FournisseurDTO updateFournisseur(int id, FournisseurDTO fournisseurDTO) {
        Fournisseur existing = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec id : " + id));

        // Mise à jour des champs
        existing.setSociete(fournisseurDTO.getSociete());
        existing.setAdresse(fournisseurDTO.getAdresse());
        existing.setContact(fournisseurDTO.getContact());
        existing.setEmail(fournisseurDTO.getEmail());
        existing.setTelephone(fournisseurDTO.getTelephone());
        existing.setVille(fournisseurDTO.getVille());
        existing.setICE(fournisseurDTO.getICE());

        Fournisseur updated = fournisseurRepository.save(existing);
        return fournisseurMapper.toDTO(updated);
    }

    public void deleteFournisseur(int id) {
        fournisseurRepository.deleteById(id);
    }
}
