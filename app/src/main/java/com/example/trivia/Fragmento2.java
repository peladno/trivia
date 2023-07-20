package com.example.trivia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento2 extends Fragment {
private TextView txtSaludo;
private RadioButton rbFacebook, rbTwitter,rbWhatsapp,rbInstagram;
private String nombre;
private Button btnEnviar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmento2() {
        // Required empty public constructor
    }


    public static Fragmento2 newInstance(String param1, String param2) {
        Fragmento2 fragment = new Fragmento2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("datos", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                nombre = result.getString("nombre");
                txtSaludo.setText("Bienvenido " + nombre + " al Juego Trivia");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtSaludo = view.findViewById(R.id.txtSaludo);
        rbWhatsapp= view.findViewById(R.id.rbWhatsapp);
        btnEnviar = view.findViewById(R.id.btnEnviar);
        rbFacebook=view.findViewById(R.id.rbFacebook);
        rbTwitter = view.findViewById(R.id.rbTwitter);
        rbInstagram= view.findViewById(R.id.rbInstagram);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Boolean isCheck=false;
                Bundle bundle1 = new Bundle();
                bundle1.putString("nombre",nombre);
                if (!rbWhatsapp.isChecked()){
                    if(rbFacebook.isChecked()){
                        bundle1.putString("msj"," lo sentimos Facebook no es incorrecto");
                        isCheck=true;
                    }

                    if(rbInstagram.isChecked()){
                        bundle1.putString("msj"," lo sentimos Instagram no es incorrecto");
                        isCheck=true;
                    }

                    if(rbTwitter.isChecked()){
                        bundle1.putString("msj"," lo sentimos Twitter no es incorrecto");
                        isCheck=true;
                    }

                }else{
                    bundle1.putString("msj"," felicidades WhatsApp es correcto!");
                    isCheck=true;
                }
                if (isCheck) {
                    getParentFragmentManager().setFragmentResult("datos1", bundle1);
                    Fragmento3 fragmento3 = new Fragmento3();
                    fragmento3.setArguments(bundle1);
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame1, fragmento3);
                    fragmentTransaction.addToBackStack("Frag2");
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(getContext(),"Debes seleccionar una opcion",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}