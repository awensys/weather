package com.example.denis.weather.view.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.denis.weather.BR;
import com.example.denis.weather.R;
import com.example.denis.weather.view.adapters.ViewPageAdapter;
import com.example.denis.weather.viewModels.MainFragmentVM;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.example.denis.weather.databinding.FragmentMainBinding;


public class MainFragment extends BindingFragment<MainFragmentVM, FragmentMainBinding> {
    ViewPager viewPager;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPageAdapter(getChildFragmentManager()));

    }



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