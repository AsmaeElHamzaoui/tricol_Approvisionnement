package com.tapprovisionnement.tricol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapprovisionnement.tricol.dto.CommandeDTO;
import com.tapprovisionnement.tricol.dto.MouvementStockDTO;
import com.tapprovisionnement.tricol.enums.StatutCommande;
import com.tapprovisionnement.tricol.enums.TypeMouvement;
import com.tapprovisionnement.tricol.repository.CommandeRepository;
import com.tapprovisionnement.tricol.repository.FournisseurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class MouvementStockControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    private int commandeId;

    @BeforeEach
    void setup() {
        // Créer un fournisseur
        var fournisseur = fournisseurRepository.save(
                com.tapprovisionnement.tricol.model.Fournisseur.builder()
                        .societe("Fournisseur Test")
                        .ville("Casablanca")
                        .adresse("Adresse Test")
                        .contact("John Doe")
                        .email("test@example.com")
                        .telephone("0600000000")
                        .ICE("ICE123456")
                        .build()
        );

        // Créer une commande associée au fournisseur
        var commande = commandeRepository.save(
                com.tapprovisionnement.tricol.model.Commande.builder()
                        .fournisseur(fournisseur)
                        .statut(StatutCommande.EN_ATTENTE)
                        .dateCommande(LocalDateTime.now())
                        .montantTotal(0.0)
                        .build()
        );

        commandeId = commande.getId();
    }

    // ----------------------------------------------------------
    // Test : création d’un mouvement de stock
    // ----------------------------------------------------------
    @Test
    void testCreateMouvementStock() throws Exception {
        MouvementStockDTO dto = MouvementStockDTO.builder()
                .commandeId(commandeId)
                .typeMouvement(TypeMouvement.ENTREE)
                .build();

        mockMvc.perform(post("/api/mouvements-stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeMouvement").value("ENTREE"))
                .andExpect(jsonPath("$.quantite").value(0)) // la quantité est calculée automatiquement depuis les lignes de commande
                .andExpect(jsonPath("$.commandeId").value(commandeId));
    }

    // ----------------------------------------------------------
    // Test : récupération d’un mouvement de stock par ID
    // ----------------------------------------------------------
    @Test
    void testGetMouvementStockById() throws Exception {
        MouvementStockDTO dto = MouvementStockDTO.builder()
                .commandeId(commandeId)
                .typeMouvement(TypeMouvement.SORTIE)
                .build();

        String response = mockMvc.perform(post("/api/mouvements-stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        MouvementStockDTO created = objectMapper.readValue(response, MouvementStockDTO.class);

        mockMvc.perform(get("/api/mouvements-stock/{id}", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeMouvement").value("SORTIE"))
                .andExpect(jsonPath("$.commandeId").value(commandeId));
    }

    // ----------------------------------------------------------
    // Test : récupération de tous les mouvements de stock
    // ----------------------------------------------------------
    @Test
    void testGetAllMouvementsStock() throws Exception {
        // Créer plusieurs mouvements
        for (TypeMouvement type : TypeMouvement.values()) {
            MouvementStockDTO dto = MouvementStockDTO.builder()
                    .commandeId(commandeId)
                    .typeMouvement(type)
                    .build();

            mockMvc.perform(post("/api/mouvements-stock")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk());
        }

        // Vérifier getAll
        mockMvc.perform(get("/api/mouvements-stock")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(TypeMouvement.values().length));
    }
}
