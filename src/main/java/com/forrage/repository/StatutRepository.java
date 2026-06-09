package com.forrage.repository;

import com.forrage.modele.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {
    public Statut findBySigle(String sigle);
}
