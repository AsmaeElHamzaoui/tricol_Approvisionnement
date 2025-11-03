package com.tapprovisionnement.tricol.repository;

import com.tapprovisionnement.tricol.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Integer> {
}
