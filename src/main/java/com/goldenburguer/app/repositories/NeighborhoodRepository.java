package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Category;
import com.goldenburguer.app.entities.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Integer> {

    Optional<Neighborhood> findNeighborhoodByName(String name);

    List<Neighborhood> findNeighborhoodByStatusTrue();
}
