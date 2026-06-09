package com.forrage.repository;

import com.forrage.modele.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Integer> {
    List<Parametre> findByStatut1IdStatutAndStatut2IdStatutOrderByDurerAsc(int idStatut1, int idStatut2);

    @Query("SELECT DISTINCT p.statut1.idStatut as idStatut1, p.statut2.idStatut as idStatut2 FROM Parametre p")
    public List<ParametreProjection> getParametres();
}
