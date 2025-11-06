package com.tapprovisionnement.tricol.repository;

import com.tapprovisionnement.tricol.model.Commande;
import com.tapprovisionnement.tricol.model.CommandeLigne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeLigneRepository extends JpaRepository<CommandeLigne,Integer> {
    List<CommandeLigne> findByCommande(Commande commande);
}
