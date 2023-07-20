package com.example.trivia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Fragmento3 extends Fragment {
private TextView txtInfo;
private String nombre;
private Button btnVolver;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Fragmento3() {
        // Required empty public constructor
    }


    public static Fragmento3 newInstance(String param1, String param2) {
        Fragmento3 fragment = new Fragmento3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getParentFragmentManager().setFragmentResultListener("datos1", this, new FragmentResultListener() {
           @Override
           public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
               String message = result.getString("msj");
               nombre = result.getString("nombre");
               txtInfo.setText(nombre + ", " + message);
           }
       });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtInfo = view.findViewById(R.id.txtInfo);
        btnVolver = view.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombre);
                getParentFragmentManager().setFragmentResult("datos",bundle);
                Fragmento2 fragmento2 = new Fragmento2();
                fragmento2.setArguments(bundle);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStackImmediate("Frag2",FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

    }
}