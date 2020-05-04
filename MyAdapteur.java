package com.example.bdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapteur extends ArrayAdapter<Personne> {

    private Context context;
    private ArrayList<Personne> personnes;

    public MyAdapteur(Context context, ArrayList<Personne> personnes){
        super(context, R.layout.personnerow, personnes);
        this.context = context;
        this.personnes = personnes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.personnerow, parent, false);
        Button ageBtn = convertView.findViewById(R.id.btn1);
        TextView nomText = convertView.findViewById(R.id.textNom);
        TextView prenomText = convertView.findViewById(R.id.textPrenom);

        Personne p = personnes.get(position);
        String monAge = String.valueOf(p.getAge());
        ageBtn.setText(monAge);
        nomText.setText(p.getNom());
        prenomText.setText(p.getPrenom());

        return convertView;

    }
}
