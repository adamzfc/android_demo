package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LiveData;
import android.view.View;

import com.adamzfc.architecturecomponentsdemo.data.vo.Account;
import com.adamzfc.architecturecomponentsdemo.data.vo.Resource;

import java.util.List;

/**
 * Created by adamzfc on 2017/7/3.
 */

public interface AccountPresenter {
    void addAccount(View view);
    LiveData<Resource<List<Account>>> getAccounts();


}
