package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:/java/basejava/storage");

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "Name1";
    private static final Resume R1;
    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME_2 = "Name2";
    private static final Resume R2;
    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_3 = "Name3";
    private static final Resume R3;
    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME_4 = "Name4";
    private static final Resume R4;

    static {
        R1 = new Resume(UUID_1, FULL_NAME_1);
        R2 = new Resume(UUID_2, FULL_NAME_2);
        R3 = new Resume(UUID_3, FULL_NAME_3);
        R4 = new Resume(UUID_4, FULL_NAME_4);

        R1.addContact(ContactType.PHONE, "+111111111111");
        R1.addContact(ContactType.MAIL, "mail@yandex.ru");
        R1.addContact(ContactType.LINKEDIN, null);
        R1.addContact(ContactType.GITHUB, "https://github.com/liuminol");
        R1.addContact(ContactType.STACKOVERFLOW, null);
        R1.addContact(ContactType.HOME_PAGE, "https://liuminol.github.io");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Красив, одинок и неплатежеспособен"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("JavaRush 27 уровень", "Создание веб-приложения - хранилище резюме в процессе обучения на курсе Javaops-Base"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java Core, Java Collections, Exceptions", "Git", "IntelliJ IDEA", "JUnit", "ООП"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://organization11.com",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://organization12.ru")));

        R2.addContact(ContactType.PHONE, "+222222");
        R2.addContact(ContactType.MAIL, "mail@gmail.com");
        R2.addContact(ContactType.SKYPE, "skype");
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization12", "http://organization12.com",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(R4);
        assertSize(4);
        assertEquals(R4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, FULL_NAME_4);
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("nothing", FULL_NAME_4));
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("nothing");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("nothing");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}