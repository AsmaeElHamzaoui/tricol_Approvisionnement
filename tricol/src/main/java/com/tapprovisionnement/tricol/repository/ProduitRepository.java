package com.tapprovisionnement.tricol.repository;

import com.tapprovisionnement.tricol.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Integer> {
}
