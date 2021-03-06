package com.urise.webapp.model;

public enum SectionType {
    PERSONAL("Контакты"),
    OBJECTIVE("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
