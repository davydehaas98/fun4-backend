package backend.service.interfaces;

import java.util.Collection;

public interface IService<T> {

    T findById(Long id);

    Collection<T> findAll();

    T save(T body);

    T edit(Long id, T body);

    void deleteById(Long id);
}
