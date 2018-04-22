package com.example.zaira.reporteciudadano;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EndPointInterface {


    @GET("verReportes.php")
    Call<ResponseReportes> verReportes(@Query("longitud") double longitud, @Query("latitud") double latitud);

    @FormUrlEncoded
    @POST("nuevoReporte.php")
    Call<Void> insertarReporte(@Field("hashtag") String hashtag,
                               @Field("comentario") String comentario,
                               @Field("latitud") double latitud,
                               @Field("longitud") double longitud);

}
