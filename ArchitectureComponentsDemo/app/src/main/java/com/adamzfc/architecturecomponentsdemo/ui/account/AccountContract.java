package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LiveData;

import com.adamzfc.architecturecomponentsdemo.BasePresenter;
import com.adamzfc.architecturecomponentsdemo.BaseView;
import com.adamzfc.architecturecomponentsdemo.data.vo.Account;
import com.adamzfc.architecturecomponentsdemo.data.vo.Resource;

import java.util.List;

/**
 * Created by adamzfc on 2017/7/19.
 */

public interface AccountContract {
    interface View extends BaseView {
        void testSth();
    }

    interface Presenter extends BasePresenter<View> {
        void addAccount();
        LiveData<Resource<List<Account>>> getAccounts();

    }
}
