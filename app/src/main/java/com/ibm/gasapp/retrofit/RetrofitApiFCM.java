package com.ibm.gasapp.retrofit;

import com.ibm.gasapp.modules.RootModel;
import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitApiFCM {

    @Headers({"Authorization: key=" + RetrofitClientFCM.fcmServerKey ,"Content-Type:application/json"})
    @POST("fcm/send")
    Call<ResponseBody> sendNotification(
            @Body RootModel rootModel
    );
}
