package com.romanovtests.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static Single<Comment[]> getCommentList(){
        return Single.create(new SingleOnSubscribe<Comment[]>() {
            @Override
            public void subscribe(SingleEmitter<Comment[]> emitter) throws Exception {
                String usersJsonStroke = getJsonFromServer("http://jsonplaceholder.typicode.com/comments", 5);
                JsonParser jsonParser = new JsonParser();
                assert usersJsonStroke != null;
                JsonArray jsonArray = (JsonArray) jsonParser.parse(usersJsonStroke);
                Comment[] comment_list = new Comment[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++){
                    comment_list[i] = parseComment(new JSONObject(jsonArray.get(i).getAsJsonObject().toString()));
                }
                emitter.onSuccess(comment_list);
            }
        });
    }

    private static Comment parseComment(JSONObject commentRoot) throws JSONException {
        String postId = commentRoot.getString("postId"),
                Id = commentRoot.getString("Id"),
                name = commentRoot.getString("name"),
                email = commentRoot.getString("email"),
                body = commentRoot.getString("body");


        return new Comment(postId, Id, name, email, body);
    }
    @org.jetbrains.annotations.Nullable
    private static String getJsonFromServer(String urlPath, int timeout) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.connect();

        int serverResponseCode = connection.getResponseCode();
        switch (serverResponseCode) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String tmpLine;
                while ((tmpLine = br.readLine()) != null) {
                    sb.append(tmpLine).append("\n");
                }
                br.close();
                return sb.toString();
            case 404:
                Log.e("SIte", "page not found!");
                break;
            case 400:
                Log.e("SIte", "Bad request!");
                break;
            case 500:
                Log.e("SIte", "Internal server error");
                break;
        }

        return null;
    }
}
class Comment{
    private String postId;
    private String Id;
    private String name;
    private String email;
    private String body;

    public Comment(String postId, String id, String name, String email, String body) {
        this.postId = postId;
        Id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPostId() {
        return postId;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}