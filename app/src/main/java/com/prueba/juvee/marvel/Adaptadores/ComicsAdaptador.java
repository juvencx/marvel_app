package com.prueba.juvee.marvel.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prueba.juvee.marvel.Objetos.Comics.Result;
import com.prueba.juvee.marvel.Objetos.Comics.ResultadoComic;
import com.prueba.juvee.marvel.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicsAdaptador extends RecyclerView.Adapter<ComicsAdaptador.ComicHolder> {

    private List<Result> mData;
    private LayoutInflater mInflater;
    private ComicsAdaptador.ItemClickListener mClickListener;

    // data is passed into the constructor
    public ComicsAdaptador(Context context, List<Result> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ComicsAdaptador.ComicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.personaje_item, parent, false);
        return new ComicsAdaptador.ComicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsAdaptador.ComicHolder holder, int position) {
        holder.myTextView.setText(mData.get(position).getTitle());

        Picasso.get().load(mData.get(position).getThumbnail().getPath()+"/portrait_fantastic."+mData.get(position).getThumbnail().getExtension()).into(holder.img_personaje);

        holder.lbl_descripcion.setText(mData.get(position).getDescription()+"...");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ComicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView img_personaje;
        TextView lbl_descripcion;

        ComicHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            img_personaje = itemView.findViewById(R.id.img_personaje);
            lbl_descripcion = itemView.findViewById(R.id.lbl_descripcion);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Result getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ComicsAdaptador.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
