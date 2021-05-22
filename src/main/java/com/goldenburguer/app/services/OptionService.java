package com.goldenburguer.app.services;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

import com.goldenburguer.app.dto.OptionDTO;
import com.goldenburguer.app.entities.Option;
import com.goldenburguer.app.entities.Product;
import com.goldenburguer.app.exceptions.BadRequestException;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.OptionRepository;
import java.util.List;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OptionService {

  private final OptionRepository repository;

  public Option findById(Integer id) {
    return repository.findById(id).orElseThrow(NotFoundException::optionNotFound);
  }

  public List<Option> listAllOptions() {
    return repository.findAll();
  }

  public List<Option> listAllOptionsActive() {
    return repository.findOptionByStatusTrue();
  }

  public Option newOption(Option option) {
    option.setName(option.getName().trim());
    validateOption(option);
    option.setStatus(true);
    return repository.save(option);
  }

  public List<Option> findAllById(List<Integer> ids) {
    return ids.stream().map(this::findById).collect(Collectors.toList());
  }

  public void validateOption(Option option) {

    if (isNull(option.getName()) || !hasText(option.getName().trim())) {
      throw BadRequestException.invalidOption();
    }

    boolean optionIsPresent =
        repository.findOptionByNameAndStatusTrue(option.getName()).isPresent();

    if (optionIsPresent) {
      throw BadRequestException.existingOption();
    }
  }

  public void deleteOption(Integer id) {
    Option option = findById(id);
    option.setStatus(false);
    repository.save(option);
  }

  public Option updateOption(OptionDTO optionDTO) {

    Option updatedOption = findById(optionDTO.getId());
    updatedOption.setName(optionDTO.getName().trim());
    validateOption(updatedOption);

    return repository.save(updatedOption);
  }


  public void reactiveOption(Integer id) {
    Option option = findById(id);
    option.setStatus(true);
    repository.save(option);
  }
}
