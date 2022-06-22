package com.example.mycartandroidv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycartandroidv2.Database.DBMain;
import com.example.mycartandroidv2.R;

public class MainActivity extends AppCompatActivity {
    EditText nomeItem, valorItem;
    NumberPicker qtdItem;
    Button criarItem, itensCriados, editarItem;
    TextView addItem, editItem;
    DBMain dbMain;
    SQLiteDatabase sqLiteDatabase;
    int idItem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbMain = new DBMain(this);

        //Metodos
        findid();
        inserirLista();
        limparDados();
        editarDados();
    }

    private void editarDados(){
        if(getIntent().getBundleExtra("dadosItens")!=null){
            Bundle bundle = getIntent().getBundleExtra("dadosItens");
            idItem=bundle.getInt("idItem");
            nomeItem.setText(bundle.getString("nomeItem"));
            valorItem.setText(bundle.getString("valorItem"));
            qtdItem.setValue(bundle.getInt("qtdItem"));
            editarItem.setVisibility(View.VISIBLE);
            editItem.setVisibility(View.VISIBLE);
            criarItem.setVisibility(View.GONE);
            addItem.setVisibility(View.GONE);

        }
    }

    private void limparDados() {
        nomeItem.setText("");
        valorItem.setText("");
        qtdItem.setValue(1);
    }

    private void inserirLista(){
        //Criar lista
        criarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("nomeItem",nomeItem.getText().toString());
                cv.put("valorItem",valorItem.getText().toString());
                cv.put("qtdItem",qtdItem.getValue());
                sqLiteDatabase=dbMain.getReadableDatabase();

                Long recinsert=sqLiteDatabase.insert("listaCompra", null, cv);
                if(recinsert!=null){
                    Toast.makeText(MainActivity.this, "Novo item adicionado!", Toast.LENGTH_SHORT).show();
                    limparDados();
                }else{
                    Toast.makeText(MainActivity.this, "Erro ao adicionar item!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Ir para listas criadas
        itensCriados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListasCriadas.class));
            }
        });

        //Confirmar edicao de item
        editarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("nomeItem",nomeItem.getText().toString());
                cv.put("valorItem",valorItem.getText().toString());
                cv.put("qtdItem",qtdItem.getValue());
                sqLiteDatabase=dbMain.getReadableDatabase();

                long recupdate=sqLiteDatabase.update("listaCompra",cv,"idItem="+idItem,null);
                if (recupdate!=-1){
                    Toast.makeText(MainActivity.this, "Item alterado!", Toast.LENGTH_SHORT).show();
                    criarItem.setVisibility(View.VISIBLE);
                    editarItem.setVisibility(View.GONE);
                    limparDados();
                }else{
                    Toast.makeText(MainActivity.this, "Erro ao alterar item!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findid(){
        nomeItem = (EditText)findViewById(R.id.edTxt_nomeItem);
        valorItem = (EditText)findViewById(R.id.edTxt_valorItem);
        qtdItem = (NumberPicker)findViewById(R.id.np_qtdItem);
        criarItem = (Button)findViewById(R.id.btn_criarItem);
        editarItem = (Button)findViewById(R.id.btn_editarItem);
        itensCriados = (Button)findViewById(R.id.btn_itensCriados);
        addItem = (TextView)findViewById(R.id.txtV_addItem);
        editItem = (TextView)findViewById(R.id.txtV_editItem);
    }
}