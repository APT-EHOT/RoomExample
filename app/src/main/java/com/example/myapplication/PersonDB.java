package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Класс базы данных Room
// Образец написания синглтона
@Database(entities = {Person.class}, version = 1)
public abstract class PersonDB extends RoomDatabase {

    // Ссылка на единственный экземпляр класса
    private static PersonDB instance;

    public abstract PersonDao personDao();

    // Метод синглтона, контролирующий число его экземпляров
    public static synchronized PersonDB getInstance(Context context) {
        if (instance == null) {
            // Инициализация базы данных
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PersonDB.class, "person_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
