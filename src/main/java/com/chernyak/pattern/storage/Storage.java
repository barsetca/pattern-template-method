package com.chernyak.pattern.storage;

import com.chernyak.pattern.model.Resume;
import java.util.List;

public interface Storage {

  void clear();

  void update(Resume resume);

  void save(Resume resume);

  Resume get(String uuid);

  void delete(String uuid);

  List<Resume> getAllSorted();

  int size();
}
