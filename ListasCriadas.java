package com.example.mycartandroidv2;

import static com.example.mycartandroidv2.Database.DBMain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mycartandroidv2.Adapter.AdapterLista;
import com.example.mycartandroidv2.Database.DBMain;
import com.example.mycartandroidv2.Model.Model;

import java.util.ArrayList;

public class ListasCriadas extends AppCompatActivity {
    DBMain dbMain;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Model> modelArrayList;
    AdapterLista adapterLista;
    RecyclerView recyclerView;
    ImageButton criarItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas_criadas);
        dbMain = new DBMain(this);

        modelArrayList = new ArrayList<>();

        findid();
        displayItens();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Btn p/ tela de criacao de itens
        criarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListasCriadas.this, MainActivity.class));
            }
        });

    }

    private ArrayList<Model> displayItens() {
        sqLiteDatabase = dbMain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLENAME+"",null);
        ArrayList<Model>modelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            int idItem = cursor.getInt(0);
            String nomeItem = cursor.getString(1);
            int qtdItem = cursor.getInt(2);
            float valorItem = cursor.getFloat(3);
            int checkItem = cursor.getInt(4);
            String nomeLista = cursor.getString(5);
            modelArrayList.add(new Model(idItem, nomeItem, qtdItem, valorItem, checkItem, nomeLista));
        }
        cursor.close();
        adapterLista = new AdapterLista(this, R.layout.card_view_lista,modelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(adapterLista);
        return modelArrayList;
    }

    private void findid() {
        recyclerView = findViewById(R.id.rv_listas);
        criarItem = (ImageButton) findViewById(R.id.ibtn_criarItem);
    }

}