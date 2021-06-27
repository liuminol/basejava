package com.urise.webapp;

import com.urise.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
    }

    @Override
    public String toString() {
        return "this is singleton";
    }

    public static void main(String[] args) {
        System.out.println(getInstance().toString());

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }
}
