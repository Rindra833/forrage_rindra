package com.forrage.repository;

import com.forrage.modele.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {
    public List<Detail> findByDevisIdDevis(int idDevis);
}
