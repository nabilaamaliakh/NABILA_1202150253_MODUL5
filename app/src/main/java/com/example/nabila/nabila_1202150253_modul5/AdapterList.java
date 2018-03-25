package com.example.nabila.nabila_1202150253_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.holder> {
    private Context mcontex;
    private List<ListData> list;
    int color;

    public AdapterList(Context cntx, List<ListData> list, int color){//Constructor
        this.mcontex=cntx;
        this.list=list;
        this.color=color;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontex).inflate(R.layout.activity_cardview, parent, false);  //membuat view baru
        holder hldr = new holder(view);
        return hldr;
    }

    //mengatur nilai dari viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        ListData data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Desc.setText(data.getDesc());
        holder.Priority.setText(data.getPrio());
        holder.card_view.setCardBackgroundColor(mcontex.getResources().getColor(this.color));
    }


    @Override
    public int getItemCount() {
        return list.size();
    } //get jumlah list ada berapa

    //dapat list dari adapter
    public ListData getData(int position){
        return list.get(position);
    }

    //delete item/data pada to do list
    public void deleteData(int i){
        list.remove(i); //delete selected item
        notifyItemRemoved(i); //notifikasi pada item yang dihapus
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{

        public TextView ToDo, Desc, Priority;
        public CardView card_view;
        public holder(View itemView){
            super(itemView);

            ToDo = itemView.findViewById(R.id.et_name);
            Desc = itemView.findViewById(R.id.et_descript);
            Priority = itemView.findViewById(R.id.et_angka);
            card_view = itemView.findViewById(R.id.cardview);
        }
    }
}
