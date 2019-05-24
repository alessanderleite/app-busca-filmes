package br.com.alessanderleite.appfilmes.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.alessanderleite.appfilmes.R;
import br.com.alessanderleite.appfilmes.adapter.MovieAdapter;
import br.com.alessanderleite.appfilmes.api.Client;
import br.com.alessanderleite.appfilmes.api.Service;
import br.com.alessanderleite.appfilmes.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();

        loadJson();
    }

    private void loadJson() {
        Service service = Client.getRetrofitInstance().create(Service.class);
        Call<Movie> call = service.getMovies();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                response.body();
                Movie movie = new Movie();

                String poster = response.body().getPoster();
                String title = response.body().getTitle();
                String year = response.body().getYear();
                String runtime = response.body().getRuntime();

                movie.setPoster(poster);
                movie.setTitle(title);
                movie.setYear(year);
                movie.setRuntime(runtime);
                movieList.add(movie);

                getRecyclerView();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void getRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieAdapter(movieList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

}
