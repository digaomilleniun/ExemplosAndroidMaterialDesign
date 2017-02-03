package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.activity.mapas.LayersDemoActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.mapas.MapaCustomizadoActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.mapas.MapaSimplesActivity;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ItemListaMapasBinding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.TransactionUtils;

/**
 * Created by rpires on 01/02/2017.
 */

public class ListaMapasAdapter extends RecyclerView.Adapter<ListaMapasAdapter.ListaMapaViewHolder>{

    private Activity activity;
    private List<ExemploCor> list;

    public ListaMapasAdapter(Activity activity, List<ExemploCor> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ListaMapaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListaMapasBinding binding = ItemListaMapasBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListaMapaViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ListaMapaViewHolder holder, int position) {
        final ExemploCor sample = list.get(holder.getAdapterPosition());
        holder.binding.setSample(sample);
        holder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        TransactionUtils.transitionToActivity(activity, MapaSimplesActivity.class, sample);
                        break;
                    case 1:
                        TransactionUtils.transitionToActivity(activity, LayersDemoActivity.class, sample);
                        break;
                    case 2:
                        TransactionUtils.transitionToActivity(activity, MapaCustomizadoActivity.class, sample);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListaMapaViewHolder extends RecyclerView.ViewHolder {

        final ItemListaMapasBinding binding;

        public ListaMapaViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
