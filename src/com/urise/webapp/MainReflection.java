package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection  {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException {
        Resume r = new Resume();
        Class<? extends Resume> resumeClass = r.getClass();

        System.out.println("Classname: " + resumeClass.getSimpleName()); //get simple classname

        System.out.println("Package: " + resumeClass.getPackageName()); //get package info

        System.out.println("Super: " + resumeClass.getSuperclass()); //get super class

        //fields
        Field field = resumeClass.getDeclaredFields()[0]; //get declared fields
        field.setAccessible(true); //allow ro get and set field
        System.out.println(field.getName()); //get field[o] name
        System.out.println(field.get(r)); //get field[0] value
        field.set(r, "new_uuid"); //set new field value

        //invoke r.toString() via reflection
        Method methodToString = resumeClass.getMethod("toString");
        try {
            System.out.println(methodToString.invoke(r));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
