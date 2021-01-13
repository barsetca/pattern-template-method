package com.chernyak.pattern.storage;

import com.chernyak.pattern.model.Resume;
import com.chernyak.pattern.storage.exception.ExistStorageException;
import com.chernyak.pattern.storage.exception.NotExistStorageException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ListStorage implements Storage {

  protected List<Resume> storage = new ArrayList<>();

  private static final Logger LOG = Logger.getLogger(ListStorage.class.getName());

  @Override
  public void clear() {
    LOG.info("clear storage + " + ListStorage.class.getSimpleName());
    storage.clear();
  }

  @Override
  public void update(Resume resume) {
    LOG.info("Update " + resume);
    Integer index = checkExist(resume.getUuid());
    storage.set(index, resume);
  }

  @Override
  public void save(Resume resume) {
    LOG.info("Save " + resume);
    checkNotExist(resume.getUuid());
    saveResume(resume);
  }

  @Override
  public Resume get(String uuid) {
    LOG.info("Get " + uuid);
    Integer index = checkExist(uuid);
    return getResume(index);
  }

  @Override
  public void delete(String uuid) {
    LOG.info("Delete " + uuid);
   Integer index = checkExist(uuid);
    deleteResume(index);
  }

  @Override
  public List<Resume> getAllSorted() {
    LOG.info("getAllSorted");
    List<Resume> resumesReady = getCopyList();
    Collections.sort(resumesReady);
    return resumesReady;
  }

  @Override
  public int size() {
    return storage.size();
  }

  private Integer checkNotExist(String uuid) {
    Integer index = getSearchKey(uuid);
    if (isExist(index)) {
      LOG.warning("Resume " + uuid + " already exist");
      throw new ExistStorageException(uuid);
    } else {
      return index;
    }
  }

  private Integer getSearchKey(String uuid) {
    for (int i = 0; i < storage.size(); i++) {
      if (uuid.equals(storage.get(i).getUuid())) {
        return i;
      }
    }
    return -1;
  }

  private Integer checkExist(String uuid) {
    Integer index = getSearchKey(uuid);
    if (!isExist(index)) {
      LOG.warning("Resume " + uuid + " not exist");
      throw new NotExistStorageException(uuid);
    } else {
      return index;
    }
  }

  private boolean isExist(Integer index) {
    return index >= 0;
  }

  private void saveResume(Resume resume) {
    storage.add(resume);
  }

  private Resume getResume(Integer index) {
    return storage.get(index);
  }

  private void deleteResume(Integer index) {
    storage.remove((int) index);
  }

  private List<Resume> getCopyList() {
    return new ArrayList<>(storage);
  }

}
