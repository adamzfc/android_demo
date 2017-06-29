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

import com.adamzfc.architecturecomponentsdemo.R;
import com.adamzfc.architecturecomponentsdemo.databinding.AccountFragmentBinding;
import com.adamzfc.architecturecomponentsdemo.util.AutoClearedValue;
import com.adamzfc.architecturecomponentsdemo.vo.Account;
import com.adamzfc.architecturecomponentsdemo.vo.Resource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class AccountFragment extends LifecycleFragment {
    AutoClearedValue<AccountFragmentBinding> binding;
    AutoClearedValue<AccountAdapter> adapter;
    private AccountViewModel accountViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AccountFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.account_fragment, container, false);
        dataBinding.setTitle("title");
        this.binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        accountViewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountViewModel.class);
        AccountAdapter adapter = new AccountAdapter();
        this.adapter = new AutoClearedValue<>(this, adapter);
        this.binding.get().accountList.setAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }

    private void initAccountList(AccountViewModel viewModel) {
        LiveData<Resource<List<Account>>> accounts = accountViewModel.getAccounts();
        accounts.observe(this, listResource -> {
            if (listResource != null && listResource.data != null) {
                adapter.get().replace(listResource.data);
            } else {
                adapter.get().replace(Collections.emptyList());
            }
        });

    }

}
