package ru.samsung.httprequestsbasics.controller;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ru.samsung.belenkov.R;
import ru.samsung.httprequestsbasics.model.entities.User;

public class UserRecycleAdapter extends RecyclerView.Adapter<UserRecycleAdapter.UserVH> {
  private List<User> userList;

  public UserRecycleAdapter(List<User> userList){
    this.userList = userList;
  }

  @NonNull
  @Override
  public UserRecycleAdapter.UserVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

    View view = inflater.inflate(R.layout.food_item, viewGroup, false);

    return new UserVH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull UserVH userVH, final int i) {
    final User user = userList.get(i);

    userVH.name.setText(user.getName());
    userVH.username.setText(user.getUsername());
    userVH.email.setText(user.getEmail());

    userVH.card.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), DetailsActivity.class);
        intent.putExtra("foodObject", user);
        v.getContext().startActivity(intent);

      }
    });
    userVH.card.setOnLongClickListener(new OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {

        userList.remove(i);
        notifyItemRemoved(i);
        notifyItemChanged(i);

        return true;
      }
    });

  }

  @Override
  public int getItemCount() {
    return userList.size();
  }

  class UserVH extends RecyclerView.ViewHolder{

    protected TextView name;

    protected TextView username;

    protected TextView email;

    protected CardView card;

    public UserVH(@NonNull View itemView) {
      super(itemView);
      username = itemView.findViewById(R.id.username);
      email = itemView.findViewById(R.id.email);
      card = itemView.findViewById(R.id.card);
    }
  }


}
