package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {

  Optional<Option> findOptionByNameAndStatusTrue(String name);

  List<Option> findOptionByStatusTrue();
}
