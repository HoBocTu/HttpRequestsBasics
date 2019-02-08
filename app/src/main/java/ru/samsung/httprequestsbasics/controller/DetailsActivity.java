package ru.samsung.httprequestsbasics.controller;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import ru.samsung.belenkov.R;
import ru.samsung.httprequestsbasics.model.entities.User;

public class DetailsActivity extends AppCompatActivity {
  private androidx.appcompat.widget.Toolbar toolbar;
  protected TextView id;

  protected TextView name;

  protected TextView username;

  protected TextView email;

  protected TextView address;

  protected TextView phone;

  protected TextView website;

  protected TextView company;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);

      User user = (User) getIntent().getSerializableExtra("foodObject");
      id = findViewById(R.id.detailsID);
      username = findViewById(R.id.detailsUsername);
      email = findViewById(R.id.detailsEmail);
      address = findViewById(R.id.detailsAddress);
      phone = findViewById(R.id.detailsPhone);
      website = findViewById(R.id.detailsWebsite);
      company = findViewById(R.id.detasilsCompany);

      id.setText(Integer.toString(user.getId()));
      username.setText(user.getUsername());
      email.setText(user.getEmail());
      address.setText(user.getAddress().toString());
      phone.setText(user.getPhone());
      website.setText(user.getWebsite());
      company.setText(user.getCompany().toString());

  }
}
