package backend.service;

import backend.model.dto.BaseDto;
import java.util.ArrayList;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T> {

  final ModelMapper modelMapper;
  protected JpaRepository repository;
  Class<T> dtoClass;

  public BaseService(JpaRepository repository, Class<T> dtoClass, ModelMapper modelMapper) {
    this.repository = repository;
    this.dtoClass = dtoClass;
    this.modelMapper = modelMapper;
  }

  public T findById(Long id) {
    for (T item : findAll()) {
      if (((BaseDto) item).getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public Collection<T> findAll() {
    Collection<T> list = new ArrayList<>();
    for (Object item : repository.findAll()) {
      list.add(modelMapper.map(
          item,
          dtoClass
      ));
    }
    return list;
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}