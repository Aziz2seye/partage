package com.example.bdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView maliste;
    ArrayAdapter<String> adapter;
    SQLiteHelper helper;
    ArrayList<String>  liste = new ArrayList<String>();
    ArrayList<Personne>  listePersonne = new ArrayList<Personne>();

    MyAdapteur adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maliste = findViewById(R.id.malistview);
        helper = new SQLiteHelper(this);
        getValues();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuapp, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (R.id.addbtn == item.getItemId()){
            //Toast.makeText(this, "addbtn", Toast.LENGTH_LONG).show();

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            final EditText editNom = new EditText(this);
            editNom.setHint("NOM");

            final EditText editPrenom = new EditText(this);
            editPrenom.setHint("Prenom");

            final EditText editAge = new EditText(this);
            editAge.setHint("Age");

            layout.addView(editNom);
            layout.addView(editPrenom);
            layout.addView(editAge);

            AlertDialog alert = new AlertDialog.Builder((this)).setTitle("INFO")
                    .setMessage(("Insert"))
                    .setView(layout)
                    .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String nomUser = editNom.getText().toString();
                            String prenomUser = editPrenom.getText().toString();
                            String ageUser = editAge.getText().toString();

                            SQLiteDatabase db = helper.getWritableDatabase();
                            ContentValues values = new ContentValues();

                            values.put("nom", nomUser);
                            values.put("prenom", prenomUser);
                            values.put("age", Integer.valueOf(ageUser));

                            long a = db.insert("Personne", null, values);

                            if (a == -1) {
                                Toast.makeText(MainActivity.this,
                                        "insert fail", Toast.LENGTH_LONG).show();
                            }
                            else{

                                Toast.makeText(MainActivity.this,
                                        "insert success", Toast.LENGTH_LONG).show();

                            }


                        }
                    }).setNegativeButton("Cancel", null)
                    .create();
            alert.show();
            getValues();
        }
        else{
            //Toast.makeText(this, "deletebtn", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getValues(){

        liste.clear();

        SQLiteDatabase db = helper.getReadableDatabase();

        String column[] = new String[]{"nom", "prenom", "age"};

        String nomPrenom[] = new String[]{"Bouiti", "Natsy"};

       /*Cursor resultat = db.query("Personne", column,"nom=? And prenom=?", nomPrenom,
               null,null, null);*/

        Cursor resultat = db.query("Personne", column, null, null,
                null,null, null);

       while(resultat.moveToNext()){
           int indiceNom = resultat.getColumnIndex("nom");
           int indicePrenom = resultat.getColumnIndex("prenom");
           int indiceAge = resultat.getColumnIndex("age");

           String nom = resultat.getString(indiceNom);
           //Log.d("123456", nom);

           String prenom = resultat.getString(indicePrenom);
           //Log.d("1234567", prenom);

           String age = resultat.getString(indiceAge);
           //Log.d("12345678", age);

           int age1 = Integer.valueOf(age);

           Personne p = new Personne(nom, prenom, age1);

           liste.add(nom);
           listePersonne.add(p);

       }
       /*adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,liste);
       maliste.setAdapter(adapter);*/

       adp = new MyAdapteur(MainActivity.this, listePersonne);
       maliste.setAdapter(adp);
    }
}
