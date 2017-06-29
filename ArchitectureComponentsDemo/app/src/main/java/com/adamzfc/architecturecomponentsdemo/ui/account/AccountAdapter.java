package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.adamzfc.architecturecomponentsdemo.R;
import com.adamzfc.architecturecomponentsdemo.databinding.AccountItemBinding;
import com.adamzfc.architecturecomponentsdemo.ui.common.DataBoundListAdapter;
import com.adamzfc.architecturecomponentsdemo.vo.Account;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class AccountAdapter extends DataBoundListAdapter<Account, AccountItemBinding> {

    @Override
    protected AccountItemBinding createBinding(ViewGroup parent) {
        AccountItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.account_item, parent, false);
        return binding;
    }

    @Override
    protected void bind(AccountItemBinding binding, Account item) {
        binding.setAccount(item);
    }

    @Override
    protected boolean areItemsTheSame(Account oldItem, Account newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    protected boolean areContentsTheSame(Account oldItem, Account newItem) {
        return TextUtils.equals(oldItem.name, newItem.name);
    }
}
