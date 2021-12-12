package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

// Data access object
@Dao
public interface PersonDao {

    @Insert
    Completable insert(Person person);

    @Query("SELECT * FROM person_table")
    Observable<List<Person>> getAllPersons();

    @Delete
    void deletePerson(Person person);

    @Update
    void updatePerson(Person person);

}
