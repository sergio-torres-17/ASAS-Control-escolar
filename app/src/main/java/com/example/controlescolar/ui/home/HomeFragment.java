package com.example.controlescolar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.controlescolar.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        homeViewModel.setContext(this.getContext());
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView txtName = binding.txtNombre;
        final TextView txtUserName = binding.txtUsuario;
        final TextView txtType = binding.txtTipo;
        homeViewModel.getName().observe(getViewLifecycleOwner(), txtName::setText);
        homeViewModel.getUserName().observe(getViewLifecycleOwner(), txtUserName::setText);
        homeViewModel.getUserType().observe(getViewLifecycleOwner(), txtType::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}