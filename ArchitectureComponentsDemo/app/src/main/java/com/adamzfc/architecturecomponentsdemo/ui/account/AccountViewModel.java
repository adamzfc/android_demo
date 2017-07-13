package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.adamzfc.architecturecomponentsdemo.data.repository.AccountRepository;
import com.adamzfc.architecturecomponentsdemo.data.vo.Account;
import com.adamzfc.architecturecomponentsdemo.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by adamzfc on 2017/6/29.
 */

public class AccountViewModel extends ViewModel implements AccountPresenter {
    private static final String TAG = AccountViewModel.class.getSimpleName();
    private final LiveData<Resource<List<Account>>> accounts;
    private final AccountRepository repository;

    @Inject
    public AccountViewModel(AccountRepository accountRepository) {
        this.accounts = accountRepository.loadAccounts();
        this.repository = accountRepository;
    }

    public LiveData<Resource<List<Account>>> getAccounts() {
        return this.accounts;
    }

    @Override
    public void addAccount(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Title");

// Set up the input
        final EditText input = new EditText(view.getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) ->
                repository.addAccount(new Account(input.getText().toString())));
        builder.setNegativeButton("Cancel", (dialog, which) ->
                dialog.cancel());
        builder.show();
        Log.d(TAG, "addAccount");
//        this.accounts.getValue().data.add(new Account(1, "account_test"));
//        repository.addAccount(new Account("account"));
    }
}
