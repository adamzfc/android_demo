package com.adamzfc.architecturecomponentsdemo.data.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.adamzfc.architecturecomponentsdemo.data.api.ApiResponse;
import com.adamzfc.architecturecomponentsdemo.data.vo.Resource;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by adamzfc on 2017/7/11.
 */

public abstract class RxNetworkBoundResource<ResultType, RequestType> {

    private CompositeDisposable mCompositeDisposable;

    private Flowable<Resource<ResultType>> result;

    public RxNetworkBoundResource() {
        Flowable<ResultType> dbSource = loadFromDb();
    }


    private void fetchFromNetwork(final Flowable<ResultType> dbSource) {
        Flowable<ApiResponse<RequestType>> apiResponse = createCall();
    }

    protected void onFetchFailed() {
    }

    public Flowable<Resource<ResultType>> asFlowable() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Flowable<ApiResponse<RequestType>> createCall();

}
