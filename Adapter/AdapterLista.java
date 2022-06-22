package com.example.mycartandroidv2.Adapter;

import static com.example.mycartandroidv2.Database.DBMain.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycartandroidv2.Database.DBMain;
import com.example.mycartandroidv2.MainActivity;
import com.example.mycartandroidv2.Model.Model;
import com.example.mycartandroidv2.R;

import java.util.ArrayList;

public class AdapterLista extends RecyclerView.Adapter<AdapterLista.ViewHolder> {
    Context context;
    ArrayList<Model> modelArrayList = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    OnItemClickListener cvListener;

    public AdapterLista(Context context, int card_view_lista, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        cvListener = listener;
    }

    @NonNull
    @Override
    public AdapterLista.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_lista,null);
        return new ViewHolder(view, cvListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLista.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Model model = modelArrayList.get(position);
        holder.txtV_nomeItem.setText(model.getNomeItem());

        holder.cardView_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("idItem", model.getIdItem());
                bundle.putString("nomeItem", model.getNomeItem());
                bundle.putInt("qtdItem", model.getQtdItem());
                bundle.putFloat("valorItem", model.getValorItem());
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("dadosItens",bundle);
                context.startActivity(intent);
            }
        });

        holder.ibtn_deletarItem.setOnClickListener(new View.OnClickListener() {
            DBMain dbMain = new DBMain(context);
            @Override
            public void onClick(View view) {
                sqLiteDatabase = dbMain.getReadableDatabase();
                long delete = sqLiteDatabase.delete(TABLENAME,"idItem="+model.getIdItem(),null);
                if(delete!=1){
                    Toast.makeText(context, "Item removido!", Toast.LENGTH_SHORT).show();
                    modelArrayList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CheckBox chk_itemCheck;
        public TextView txtV_nomeItem;
        public TextView txtV_qtdItem;
        public TextView txtV_valorItem;
        public ImageButton ibtn_editarItem;
        public ImageButton ibtn_deletarItem;
        public CardView cardView_lista;
        public Context context;

        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            chk_itemCheck = (CheckBox)itemView.findViewById(R.id.chk_itemCheck);
            txtV_nomeItem = (TextView)itemView.findViewById(R.id.txtV_nomeItem);
            txtV_qtdItem = (TextView)itemView.findViewById(R.id.txtV_qtdItem);
            txtV_valorItem = (TextView)itemView.findViewById(R.id.txtV_valorItem);
            ibtn_editarItem = (ImageButton)itemView.findViewById(R.id.ibtn_editarItem);
            ibtn_deletarItem = (ImageButton)itemView.findViewById(R.id.ibtn_deletarItem);
            cardView_lista = (CardView)itemView.findViewById(R.id.cardView_lista);

        }
    }
}
