package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public Resume[] getStorage() {
        return storage;
    }


    public void setStorage(Resume[] storage) {
        this.storage = storage;
    }

    public void clear() {
        storage = new Resume[STORAGE_LIMIT];
        this.size = 0;
    }

    public void save(Resume r) {
        if (checkExistenceInStorage(r)) {
            System.out.println("ERROR: the resume existence yet in storage");
            return;
        }

        storage[size] = r;
        size++;
    }

    public void update(Resume r) {
        int index = getIndex(r);

        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: this resume not exist");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == null) break;

            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }

        System.out.println("ERROR: resume with this uuid not exist");
        return null;
    }

    public void delete(String uuid) {
        if (!checkExistenceInStorage(new Resume(uuid))) {
            System.out.println("ERROR: storage hasn't a resume with this uuid");
            return;
        }

        List<Resume> result = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            if (!uuid.equals(storage[i].getUuid())) {
                result.add(storage[i]);
            }
        }

        size--;
        storage = result.toArray(new Resume[result.size()]);
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

    /**
     * check whether existence added resume in storage
     */
    private boolean checkExistenceInStorage(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) return true;
        }

        return false;
    }

    /**
     * returns the index of existing resume or -1 if not
     */
    private int getIndex(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) return i;
        }

        return -1;
    }
}
