package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MainCollection {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "Name4");

    public static void main(String[] args) {
        //arraylist implementation
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println("arraylist: " + collection.toString());

        //hashmap implementation
        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        //плохая реализация получения всех значений, так как операция обращения в hashmap дорогая
        for (String key : map.keySet()) {
            System.out.println("By keySet iteration: " + map.get(key));
        }

        //хорошая реализация получения всех значений, обращение к паре ключ-значение
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println("By entrySet iteration: " + entry.getValue());
        }
    }
}
