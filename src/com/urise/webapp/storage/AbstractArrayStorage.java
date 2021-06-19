package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        storage = new Resume[STORAGE_LIMIT];
        this.size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) {
            System.out.println("ERROR: Resume " + r.getUuid() + " already exist");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());

        if (index > 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: resume " + r.getUuid() + " not exist");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }

        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("ERROR: resume " + uuid + " not exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return Array containing only resumes in storage without nulls
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    /**
     * @return count of resumes in storages
     */
    public int size() {
        return this.size;
    }

    protected abstract void fillDeletedElement(int index);

    /**
     * @return the index of existing resume or -1 if not
     */
    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);
}
