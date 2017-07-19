package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adamzfc.architecturecomponentsdemo.R;
import com.adamzfc.architecturecomponentsdemo.data.vo.Account;
import com.adamzfc.architecturecomponentsdemo.data.vo.Resource;
import com.adamzfc.architecturecomponentsdemo.data.vo.Status;
import com.adamzfc.architecturecomponentsdemo.databinding.AccountFragmentBinding;
import com.adamzfc.architecturecomponentsdemo.di.Injectable;
import com.adamzfc.architecturecomponentsdemo.util.AutoClearedValue;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class AccountFragment extends LifecycleFragment implements AccountContract.View, Injectable {
    AutoClearedValue<AccountFragmentBinding> binding;
    AutoClearedValue<AccountAdapter> adapter;
    private AccountViewModel accountViewModel;

    @Inject
    AccountContract.Presenter mPresenter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    @Override
    public void showToast(String message) {
        Toast.makeText(getContext().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AccountFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.account_fragment, container, false);
        this.binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        accountViewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountViewModel.class);
        AccountAdapter adapter = new AccountAdapter();
        this.adapter = new AutoClearedValue<>(this, adapter);
        this.binding.get().accountList.setAdapter(adapter);
        mPresenter.bindView(this);
        this.binding.get().setPresenter(mPresenter);
        initAccountList(accountViewModel);
    }

    private void initAccountList(AccountViewModel viewModel) {
        LiveData<Resource<List<Account>>> accounts = viewModel.getAccounts();
        accounts.observe(this, listResource -> {
            if (listResource != null && listResource.data != null) {
                if (listResource.status.equals(Status.ERROR)) {
                    Toast.makeText(getActivity(), listResource.message, Toast.LENGTH_SHORT).show();
                }
                adapter.get().replace(listResource.data);
            } else {
                adapter.get().replace(Collections.emptyList());
            }
        });
    }

}
