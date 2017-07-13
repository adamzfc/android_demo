package com.adamzfc.architecturecomponentsdemo.data.api;

import android.arch.lifecycle.LiveData;

import com.adamzfc.architecturecomponentsdemo.data.vo.Account;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by adamzfc on 2017/6/29.
 */

public interface FinancialService {
    @GET("/api/account")
    LiveData<ApiResponse<List<Account>>> getAccounts();

    @Headers({"Content-Type: application/json"})
    @POST("/api/account")
    Flowable<ApiResult> addAccount(@Body Account account);

    @GET("/api/account")
    Flowable<ApiResponse<List<Account>>> getAccountsRx();

}
