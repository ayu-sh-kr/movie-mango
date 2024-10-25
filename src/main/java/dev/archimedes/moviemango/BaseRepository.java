package dev.archimedes.moviemango;

import java.util.Optional;

public interface BaseRepository <T, I>{

  Optional<T> findById(I id);

  boolean existsById(I id);

  T getReferenceById(I id);

  T save(T t);

  void deleteById(I id);

}
