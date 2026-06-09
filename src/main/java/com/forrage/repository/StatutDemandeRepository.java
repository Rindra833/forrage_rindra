package com.forrage.repository;

import com.forrage.modele.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatutDemandeRepository extends JpaRepository<StatutDemande, Integer> {
    public List<StatutDemande> findStatutDemandeByDemandeReference(String reference);

    public StatutDemande findStatutDemandeByDemandeReferenceAndStatutIdStatut(String reference, int idStatut);

    public StatutDemande findFirstByDemandeReferenceOrderByDateDesc(String reference);

    public StatutDemande findFirstByDemandeReferenceAndDateLessThanOrderByDateDesc(String reference,LocalDateTime date);

    public StatutDemande findFirstByDemandeReferenceAndDateGreaterThanOrderByDateAsc(String reference,LocalDateTime date);
}
