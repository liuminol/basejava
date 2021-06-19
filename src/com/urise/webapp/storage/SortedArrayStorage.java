package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

//    @Override
//    public void save(Resume r) {
//        if (size == 0) {
//            storage[0] = r;
//            size++;
//            return;
//        }
//
//        int index = getIndex(r.getUuid());
//        if (index >= 0) {
//            System.out.println("ERROR: the resume existence yet in storage");
//            return;
//        }
//
//        index = Math.abs(index) - 1;
//        size++;
//
//        insertResume(r, index);
//    }

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = size - index - 1;

        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume r, int index) {
//      https://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array
        int insertInd = -index - 1;
        System.arraycopy(storage, insertInd, storage, insertInd + 1, size - insertInd);
        storage[insertInd] = r;
    }

    /**
     * insert Resume in sorted array storage by index
     *
     * @param r     - inserted resume
     * @param index - index where resume will be inserted
     */
    private void insertResume(Resume r, int index) {
        Resume[] result = new Resume[STORAGE_LIMIT];
        for (int i = 0; i < index; i++) {
            result[i] = storage[i];
        }

        result[index] = r;
        for (int i = index + 1; i < size; i++) {
            result[i] = storage[i - 1];
        }

        storage = result.clone();
    }
}
