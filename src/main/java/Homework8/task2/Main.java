package Homework8.task2;


import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class Main {


    public static void main(String[] args) {
        Clazz clazz1 = new Clazz();
        serialize(clazz1, "file1");

        Clazz clazz2 = (Clazz) deSerialize("file1");
        System.out.println(clazz2.a);
        System.out.println(clazz2.aClass.toString());
    }

    public static void serialize(Object object, String file) {
        Class<?> objClass = object.getClass();

        Field[] fields = objClass.getDeclaredFields();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream((new FileOutputStream(file)))) {

            objectOutputStream.writeUTF(objClass.getName());

            for (Field f : fields) {
                f.setAccessible(true);
                if (!f.getClass().isPrimitive()){ //todo какое условие позволит определить, что объект не примитив
                    objectOutputStream.writeUTF(serializeField(f));
                } else {
                    objectOutputStream.writeObject(f.get(object));
                }
            }
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String serializeField(Object f){
        Class <?> fieldClass = f.getClass();
        Field[] fields = fieldClass.getDeclaredFields();
        String Rezult = fieldClass.getName();
        for (Field field: fields) {
            Rezult+=field;
        }
        return Rezult;
    }

    public static Object deSerialize(String file) {
        Object object = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {

            String className = inputStream.readUTF();

            //todo 1) разобраться чего идея от меня хочет
            //     2) Как десериализовать непримитивные поля?

            Class clazz = Class.forName(className);

            object = clazz.getConstructor().newInstance(); // Создаем новый объект с полями по умолчанию

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(object, inputStream.readObject());
            }

        } catch (ClassNotFoundException | IOException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return object;
    }

}
