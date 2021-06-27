package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume r, String uuid) {
        map.put((String) uuid, r);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    protected void doUpdate(Resume r, String uuid) {
        map.put((String) uuid, r);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get((String) uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
