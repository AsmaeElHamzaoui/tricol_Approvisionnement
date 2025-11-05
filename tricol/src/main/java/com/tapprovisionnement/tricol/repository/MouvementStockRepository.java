package com.tapprovisionnement.tricol.repository;

import com.tapprovisionnement.tricol.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementStockRepository extends JpaRepository<MouvementStock,Integer> {
}
