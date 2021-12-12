package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonDB personDB = PersonDB.getInstance(this); // Создание экземпляра класса БД
        PersonDao personDao = personDB.personDao(); // Создание экземпляра DAO

        personDao.insert(new Person("Test1", 'M', 1213))
                .subscribeOn(Schedulers.io()).subscribe(() -> { });
        personDao.insert(new Person("Test2", 'M', 124))
                .subscribeOn(Schedulers.io()).subscribe(() -> { });
        personDao.insert(new Person("Test3", 'M', 3214))
                .subscribeOn(Schedulers.io()).subscribe(() -> { });

        // Rx цепочка по загрузке данных из БД
        personDao.getAllPersons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Person>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onNext(@NonNull List<Person> people) {
                        StringBuilder result = new StringBuilder();
                        for (Person p : people) {
                            result.append(p).append("\n");
                        }
                        Log.d("MyLog", result.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) { }

                    @Override
                    public void onComplete() { }
                });


    }
}