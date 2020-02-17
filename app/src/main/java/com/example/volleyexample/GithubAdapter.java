package com.example.volleyexample;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;


public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubHolder> {

    private Context context;
    private User[] data;

    public GithubAdapter(Context context, User[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GithubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.git_view, parent, false);
        return new GithubHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GithubHolder holder, int position) {
        final User user = data[position];
        holder.tv_userName.setText(user.getLogin());
        Glide.with(context).load(user.getAvatarUrl()).into(holder.iv_userImage);//using glide to download image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, user.getLogin(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GithubHolder extends RecyclerView.ViewHolder {
        TextView tv_userName;
        ImageView iv_userImage;

        public GithubHolder(@NonNull View itemView) {
            super(itemView);
            tv_userName = itemView.findViewById(R.id.textView_name);
            iv_userImage = itemView.findViewById(R.id.imageView_user);
        }
    }
}
