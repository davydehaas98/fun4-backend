package backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ServiceBase<T> {

  protected final JpaRepository repository;
  final Class<T> dtoClass;
  final ModelMapper modelMapper;

  public ServiceBase(JpaRepository repository, Class<T> dtoClass, ModelMapper modelMapper) {
    this.repository = repository;
    this.dtoClass = dtoClass;
    this.modelMapper = modelMapper;
  }

  public T findById(Long id) {
    return modelMapper.map(
        repository.findById(id),
        dtoClass
    );
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
