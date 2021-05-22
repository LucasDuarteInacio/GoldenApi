package com.goldenburguer.app.services;

import com.goldenburguer.app.dto.NeighborhoodDTO;
import com.goldenburguer.app.entities.Category;
import com.goldenburguer.app.entities.Neighborhood;
import com.goldenburguer.app.exceptions.BadRequestException;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.NeighborhoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;


    public Neighborhood findById(Integer id) {
        return neighborhoodRepository.findById(id).orElseThrow(NotFoundException::NeighborhoodNotFound);
    }

    public List<Neighborhood> findAll() {
        return neighborhoodRepository.findAll();
    }

    public List<Neighborhood> listAllNeighborhoodsActive() {
        return neighborhoodRepository.findNeighborhoodByStatusTrue();
    }

    public Neighborhood newNeighborhood(NeighborhoodDTO neighborhoodDTO) {
        Boolean neighborhoodIsPresent = neighborhoodRepository.findNeighborhoodByName(neighborhoodDTO.getName()).isPresent();

        Neighborhood neighborhood = Neighborhood.builder()
                .name(neighborhoodDTO.getName())
                .status(true)
                .build();

        if(neighborhoodIsPresent){
            throw BadRequestException.existingNeighborhood();
        }
        return neighborhoodRepository.save(neighborhood);
    }

    public Neighborhood updateNeighborhood(NeighborhoodDTO neighborhoodDTO) {
        Neighborhood neighborhood = findById(neighborhoodDTO.getId());
       neighborhood.setName(neighborhoodDTO.getName());
        return neighborhoodRepository.save(neighborhood);
    }

    public Neighborhood disableNeighborhood(Integer idNeighborhood) {
        Neighborhood neighborhood = findById(idNeighborhood);
        neighborhood.setStatus(false);
        return neighborhoodRepository.save(neighborhood);
    }

    public Neighborhood reactiveNeighborhood(Integer idNeighborhood) {
        Neighborhood neighborhood = findById(idNeighborhood);
        neighborhood.setStatus(true);
        return neighborhoodRepository.save(neighborhood);
    }


}
