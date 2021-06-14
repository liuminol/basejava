import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        storage = new Resume[10000];
        this.size = 0;
    }

    void save(Resume r) {
        //TODO check if resume not present
        storage[size] = r;
        size++;
    }

    public void update(Resume r) {
        //TODO check if resume present
        System.out.printf("ERROR");
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == null) break;

            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        //TODO check if resume present
        List<Resume> result = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            if (!uuid.equals(storage[i].uuid)) {
                result.add(storage[i]);
            }
        }

        size--;

        storage = (Resume[]) result.toArray(new Resume[result.size()]);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (storage[0] == null) {
            System.out.println("Empty");
            return null;
        }

        List<Resume> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(storage[i]);
        }

        return result.toArray(new Resume[result.size()]);
    }

    int size() {
        return this.size;
    }
}
