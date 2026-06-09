package com.forrage.repository;

import com.forrage.modele.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {
    public Demande findByReferenceContainingIgnoreCase(String reference);
}
