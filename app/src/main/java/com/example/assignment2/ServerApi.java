package com.example.assignment2;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

interface ServerApi {
    @Multipart
    @POST("upload")
    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file,
                                    @Part("file") RequestBody name);

    @Multipart
    @POST("upload")
    Call<ServerResponse> uploadMulFile(@Part MultipartBody.Part file0,
                                       @Part MultipartBody.Part file1,
                                       @Part MultipartBody.Part file2);
    @Multipart
    @POST("upload")
    Call<ServerResponse> uploadFile1(@Part MultipartBody.Part file);
}