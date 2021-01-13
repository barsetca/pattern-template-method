package com.chernyak.pattern.storage;

import com.chernyak.pattern.model.Resume;
import com.chernyak.pattern.storage.exception.ExistStorageException;
import com.chernyak.pattern.storage.exception.NotExistStorageException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MapStorage implements Storage {

  protected Map<String, Resume> storage = new HashMap<>();

  private static final Logger LOG = Logger.getLogger(ListStorage.class.getName());

  @Override
  public void clear() {
    LOG.info("clear storage + " + MapStorage.class.getSimpleName());
    storage.clear();
  }

  @Override
  public void update(Resume resume) {
    LOG.info("Update " + resume);
    String uuid = checkExist(resume.getUuid());
    storage.put(uuid, resume);
  }

  @Override
  public void save(Resume resume) {
    LOG.info("Save " + resume);
    String uuid = checkNotExist(resume.getUuid());
    storage.put(uuid, resume);
  }

  @Override
  public Resume get(String uuid) {
    LOG.info("Get " + uuid);
    return storage.get(uuid);
  }

  @Override
  public void delete(String uuid) {
    LOG.info("Delete " + uuid);
    checkExist(uuid);
    storage.remove(uuid);
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
    LOG.info("size");
    return storage.size();
  }

  private String checkExist(String uuid) {
    if (!isExist(uuid)) {
      LOG.warning("Resume " + uuid + " not exist");
      throw new NotExistStorageException(uuid);
    } else {
      return uuid;
    }
  }

  private boolean isExist(String uuid) {
    return storage.containsKey(uuid);
  }

  private String checkNotExist(String uuid) {
    if (isExist(uuid)) {
      LOG.warning("Resume " + uuid + " already exist");
      throw new ExistStorageException(uuid);
    } else {
      return uuid;
    }
  }

  private List<Resume> getCopyList() {
    return new ArrayList<>(storage.values());
  }
}
