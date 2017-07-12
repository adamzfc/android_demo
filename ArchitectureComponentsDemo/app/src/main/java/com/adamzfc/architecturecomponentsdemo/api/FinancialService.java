package com.adamzfc.architecturecomponentsdemo.api;

import android.arch.lifecycle.LiveData;

import com.adamzfc.architecturecomponentsdemo.vo.Account;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by adamzfc on 2017/6/29.
 */

public interface FinancialService {
    @GET("/api/account")
    LiveData<ApiResponse<List<Account>>> getAccounts();

    @GET("/api/account")
    Flowable<ApiResponse<List<Account>>> getAccountsRx();

}
