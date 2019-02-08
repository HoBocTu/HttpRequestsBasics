package ru.samsung.httprequestsbasics.controller;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.samsung.belenkov.R;
import ru.samsung.httprequestsbasics.controller.httpTask.UserTask;
import ru.samsung.httprequestsbasics.model.entities.User;


public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView userRecycler = findViewById(R.id.foodRecycler);
        UserTask task = new UserTask();
        task.execute();

        ArrayList<User> userArrayList = new ArrayList<>(Arrays.asList(task.getUsers()));

        userRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        userRecycler.setAdapter(new UserRecycleAdapter(userArrayList));

    }
}
