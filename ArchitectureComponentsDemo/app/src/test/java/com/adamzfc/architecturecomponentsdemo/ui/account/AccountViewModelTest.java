package com.adamzfc.architecturecomponentsdemo.ui.account;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.adamzfc.architecturecomponentsdemo.data.repository.AccountRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.mock;

/**
 * Created by adamzfc on 2017/7/20.
 */
@RunWith(JUnit4.class)
public class AccountViewModelTest {
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private AccountViewModel mAccountViewModel;
    private AccountRepository mAccountRepository;

    @Before
    public void setup() {
        mAccountRepository = mock(AccountRepository.class);
        mAccountViewModel = new AccountViewModel(mAccountRepository);
    }

}
