package ru.samsung.httprequestsbasics.controller.httpTask;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import ru.samsung.httprequestsbasics.model.api.JsonPlaceholderApi;
import ru.samsung.httprequestsbasics.model.entities.User;

public class UserTask extends AsyncTask<Void, Void, Void> {
    User[] users;
    @Override
    protected Void doInBackground(Void... voids) {
        JsonPlaceholderApi api = new JsonPlaceholderApi("https://jsonplaceholder.typicode.com/users");
        try {
            users = api.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User[] getUsers(){
        return users;
    }
}
