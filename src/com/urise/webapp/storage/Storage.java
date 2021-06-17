package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {


    void clear();

    void save(Resume r);

    void update(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    int size();
}
