/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.adamzfc.architecturecomponentsdemo.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.adamzfc.architecturecomponentsdemo.data.api.FinancialService;
import com.adamzfc.architecturecomponentsdemo.data.api.LogInterceptor;
import com.adamzfc.architecturecomponentsdemo.data.db.AccountDao;
import com.adamzfc.architecturecomponentsdemo.data.db.FinancialDb;
import com.adamzfc.architecturecomponentsdemo.data.db.TransactionDao;
import com.adamzfc.architecturecomponentsdemo.util.AppConstants;
import com.adamzfc.architecturecomponentsdemo.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton @Provides
    FinancialService provideFinancialService() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new LogInterceptor()).build();
        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build()
                .create(FinancialService.class);
    }

    @Singleton @Provides
    FinancialDb provideDb(Application app) {
        return Room.databaseBuilder(app, FinancialDb.class,"financial.db").build();
    }

    @Singleton @Provides
    AccountDao provideUserDao(FinancialDb db) {
        return db.accountDao();
    }

    @Singleton @Provides
    TransactionDao provideTransactionDao(FinancialDb db) {
        return db.transactionDao();
    }

    @Provides
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

}
