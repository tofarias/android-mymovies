package com.example.tiago.mymovies.OMDBApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBService {

    @GET("?apikey=87ac4dc6")
    Call<Movie> detailTitle(@Query("t") String t);

    /*@GET("ws/{state}/{city}/{street}/json/")
    Call<List<Address>> serchInStreets(
            @Path("state") String state,
            @Path("city") String city,
            @Path("street") String street);*/

}
