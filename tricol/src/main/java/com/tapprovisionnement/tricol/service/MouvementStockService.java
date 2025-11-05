package com.tapprovisionnement.tricol.service;

import com.tapprovisionnement.tricol.dto.MouvementStockDTO;
import com.tapprovisionnement.tricol.mapper.MouvementStockMapper;
import com.tapprovisionnement.tricol.model.MouvementStock;
import com.tapprovisionnement.tricol.model.CommandeLigne;
import com.tapprovisionnement.tricol.repository.MouvementStockRepository;
import com.tapprovisionnement.tricol.repository.CommandeLigneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MouvementStockService {

    private final MouvementStockRepository mouvementStockRepository;
    private final MouvementStockMapper mouvementStockMapper;
    private final CommandeLigneRepository commandeLigneRepository;

    //getAll
    public Page<MouvementStockDTO> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<MouvementStock> mouvements = mouvementStockRepository.findAll(pageable);
        return mouvements.map(mouvementStockMapper::toDTO);
    }

    //getById
    public MouvementStockDTO getById(int id){
        MouvementStock mouvementStock = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("mouvementStock not found"));
        return mouvementStockMapper.toDTO(mouvementStock);
    }

    //create
    public MouvementStockDTO create(MouvementStockDTO dto){
        MouvementStock mouvement = mouvementStockMapper.toEntity(dto);

        CommandeLigne ligne = commandeLigneRepository.findById(dto.getLigneCommandeId())
                .orElseThrow(() -> new RuntimeException("ligne commande not found"));
        mouvement.setLigneCommande(ligne);

        MouvementStock saved = mouvementStockRepository.save(mouvement);
        return mouvementStockMapper.toDTO(saved);
    }

    //update
    public MouvementStockDTO update(int id, MouvementStockDTO dto){
        MouvementStock mouvement = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("mouvementStock not found"));

        CommandeLigne ligne = commandeLigneRepository.findById(dto.getLigneCommandeId())
                .orElseThrow(() -> new RuntimeException("ligne commande not found"));

        mouvement.setDateMouvement(java.time.LocalDate.parse(dto.getDateMouvement()));
        mouvement.setTypeMouvement(dto.getTypeMouvement());
        mouvement.setQuantite(dto.getQuantite());
        mouvement.setLigneCommande(ligne);

        MouvementStock saved = mouvementStockRepository.save(mouvement);
        return mouvementStockMapper.toDTO(saved);
    }

    //delete
    public void delete(int id){
        mouvementStockRepository.deleteById(id);
    }
}
