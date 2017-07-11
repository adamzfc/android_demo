package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LiveData;

import com.adamzfc.architecturecomponentsdemo.vo.Account;
import com.adamzfc.architecturecomponentsdemo.vo.Resource;

import java.util.List;

/**
 * Created by adamzfc on 2017/7/3.
 */

public interface AccountPresenter {
    void addAccount();
    LiveData<Resource<List<Account>>> getAccounts();
}
