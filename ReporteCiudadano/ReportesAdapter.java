package com.example.zaira.reporteciudadano;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReportesAdapter extends RecyclerView.Adapter<ReportesAdapter.ReportesViewHolder> {
    private Context context;
    private List<Reporte> reportes;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

   /* public ReportesAdapter(List<Reporte> reportes) {
        this.reportes = reportes;
    }
*/
    public ReportesAdapter(Context context, List<Reporte> reportes, RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.reportes = reportes;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
        this.context = context;
    }



    @Override
    public ReportesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card, parent, false);
        return new ReportesAdapter.ReportesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReportesViewHolder holder, final int position) {
        Reporte reporteActual = reportes.get(position);
        holder.hashtag.setText(reporteActual.getHashtag());
        holder.comentario.setText(reporteActual.getComentario());

        holder.distancia.setText(String.valueOf(String.format("%.1f", reporteActual.getDistancia())+" m" ));

        if(reporteActual.getDistancia()<=5.00){
            holder.distancia.setTextColor(context.getResources().getColor(R.color.rojo));
        } else if (reporteActual.getDistancia() > 5.00) {
            holder.distancia.setTextColor(context.getResources().getColor(R.color.azul));
        }

        holder.idCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewOnItemClickListener.onClick(holder.idCard,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportes.size();
    }

    public static  class ReportesViewHolder extends RecyclerView.ViewHolder{
        TextView hashtag, comentario, distancia;
        CardView idCard;

        public ReportesViewHolder(View itemView) {
            super(itemView);
            idCard = itemView.findViewById(R.id.idCard);
            hashtag = itemView.findViewById(R.id.hashtag);
            comentario = itemView.findViewById(R.id.comentario);
            distancia = itemView.findViewById(R.id.distancia);

        }
    }


}





