package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume r) {
        if (storage.containsKey(r.getUuid())) throw new ExistStorageException(r.getUuid());

        storage.put(r.getUuid(), r);
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public Resume get(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            String key = entry.getKey();
            if (uuid.equals(key)) return entry.getValue();
        }

        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            String key = entry.getKey();
            if (uuid.equals(key)) {
                storage.remove(key);
                return;
            }
        }

        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[this.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {

    }

    @Override
    protected Integer getSearchKey(String uuid) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }
}
