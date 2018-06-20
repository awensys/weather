package com.example.denis.weather.fragments;

import com.example.denis.weather.BR;
import com.example.denis.weather.R;
import com.example.denis.weather.viewModels.MainFragmentVM;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.example.denis.weather.databinding.FragmentMainBinding;


public class MainFragment extends BindingFragment<MainFragmentVM, FragmentMainBinding> {

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected MainFragmentVM onCreateViewModel(FragmentMainBinding binding) {
        return new MainFragmentVM(this);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

}