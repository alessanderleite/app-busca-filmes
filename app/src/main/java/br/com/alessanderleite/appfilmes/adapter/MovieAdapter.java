package br.com.alessanderleite.appfilmes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.alessanderleite.appfilmes.R;
import br.com.alessanderleite.appfilmes.model.Movie;
import br.com.alessanderleite.appfilmes.ui.DetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        Glide.with(context)
                .load(movie.getPoster())
                .into(holder.mPoster);

        holder.mTitle.setText(movie.getTitle());
        holder.mYear.setText(movie.getYear());
        holder.mRuntime.setText(movie.getRuntime());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mPoster;
        private TextView mTitle;
        private TextView mYear;
        private TextView mRuntime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mPoster = (ImageView) itemView.findViewById(R.id.img_poster);
            mTitle = (TextView) itemView.findViewById(R.id.txt_title);
            mYear = (TextView) itemView.findViewById(R.id.txt_year);
            mRuntime = (TextView) itemView.findViewById(R.id.txt_runtime);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Movie movie = movieList.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("poster", movie.getPoster());
                intent.putExtra("title", movie.getTitle());
                intent.putExtra("year", movie.getYear());
                intent.putExtra("runtime", movie.getRuntime());
                context.startActivity(intent);
            }
        }
    }
}
