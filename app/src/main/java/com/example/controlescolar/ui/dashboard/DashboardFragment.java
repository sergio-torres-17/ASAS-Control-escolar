package com.example.controlescolar.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.controlescolar.ClasePreview;
import com.example.controlescolar.LocalStorage.DbLite;
import com.example.controlescolar.databinding.FragmentDashboardBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private Button btn;
    private TextView textView;
    private boolean isProfesor;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btn = binding.button;
        isProfesor =  (new DbLite(getContext(), "",null, 1).getInformationCurrentUser().getTipoUsuario().equals("Profesor"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(),ClasePreview.class));
                IntentIntegrator integrator=new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector Control Escolar");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
        btn.setText((isProfesor)? "Cerrar recibimiento clase":"Escanear pase de lista");
        //Toast.makeText(getContext(), "Tipo de alumno "+new DbLite(getContext(), "",null, 1).getInformationCurrentUser().getTipoUsuario(), Toast.LENGTH_LONG).show();;
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}