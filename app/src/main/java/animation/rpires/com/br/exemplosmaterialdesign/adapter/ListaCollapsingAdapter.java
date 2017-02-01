package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


import animation.rpires.com.br.exemplosmaterialdesign.activity.ParallaxCollapsingToolbarLayoutActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.PinCollapsingToolbarLayoutActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.TabCollapsingToolbarLayoutActivity;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ItemListaCollapsingBinding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.TransactionUtils;

/**
 * Created by rpires on 31/01/2017.
 */

public class ListaCollapsingAdapter extends RecyclerView.Adapter<ListaCollapsingAdapter.ListaCollapsingViewHolder>{

    private Activity activity;
    private List<ExemploCor> list;

    public ListaCollapsingAdapter(Activity activity, List<ExemploCor> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ListaCollapsingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListaCollapsingBinding binding = ItemListaCollapsingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListaCollapsingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ListaCollapsingViewHolder holder, int position) {
        final ExemploCor sample = list.get(holder.getAdapterPosition());
        holder.binding.setSample(sample);
        holder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        TransactionUtils.transitionToActivity(activity, PinCollapsingToolbarLayoutActivity.class, sample);
                        break;
                    case 1:
                        TransactionUtils.transitionToActivity(activity, ParallaxCollapsingToolbarLayoutActivity.class, sample);
                        break;
                    case 2:
                        TransactionUtils.transitionToActivity(activity, TabCollapsingToolbarLayoutActivity.class, sample);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListaCollapsingViewHolder extends RecyclerView.ViewHolder {

        final ItemListaCollapsingBinding binding;

        public ListaCollapsingViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
