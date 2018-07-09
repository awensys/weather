package com.example.denis.weather.view.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.denis.weather.BR;
import com.example.denis.weather.R;
import com.example.denis.weather.view.adapters.ViewPageAdapter;

import com.example.denis.weather.viewModels.MainFragmentVM;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.example.denis.weather.databinding.FragmentMainBinding;

import java.util.Objects;


public class MainFragment extends BindingFragment<MainFragmentVM, FragmentMainBinding> implements MainFragmentVM.OnUpdateViewPager {
    public static final String TAG = "MainFragment";
    ViewPager viewPager;
    public ViewPageAdapter viewPageAdapter;
    public static MainFragment instance;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        //TODO почему єтот адаптер null?
        viewPageAdapter = new ViewPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPageAdapter);

    }


    public MainFragment() {
        // Required empty public constructor
        if (instance != null) {
            viewPageAdapter = newInstance().viewPageAdapter;
            viewPager = newInstance().viewPager;
        }
    }

    public static MainFragment newInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;


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


    @Override
    public void onUpdateView() {
        Log.i(TAG, "onUpdateView: main fragment get click");
        viewPageAdapter.notifyDataSetChanged();
    }
}