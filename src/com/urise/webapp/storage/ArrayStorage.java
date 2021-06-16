package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public Resume[] getStorage() {
        return storage;
    }

    public void setStorage(Resume[] storage) {
        this.storage = storage;
    }

    public void clear() {
        storage = new Resume[10000];
        this.size = 0;
    }

    public void save(Resume r) {
        //TODO check if resume not present
        storage[size] = r;
        size++;
    }

    public void update(Resume r) {
        //TODO check if resume present
        System.out.printf("ERROR");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == null) break;

            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        //TODO check if resume present
        List<Resume> result = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            if (!uuid.equals(storage[i].getUuid())) {
                result.add(storage[i]);
            }
        }

        size--;

        storage = (Resume[]) result.toArray(new Resume[result.size()]);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        List<Resume> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(storage[i]);
        }

        return result.toArray(new Resume[result.size()]);
    }

    public int size() {
        return this.size;
    }
}
