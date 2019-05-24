package br.com.alessanderleite.appfilmes.api;

import br.com.alessanderleite.appfilmes.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    //http://www.omdbapi.com/?i=tt3896198&apikey=a45a2a24
    @GET("?i=tt3896198&apikey=a45a2a24")
    Call<Movie> getMovies();
}
