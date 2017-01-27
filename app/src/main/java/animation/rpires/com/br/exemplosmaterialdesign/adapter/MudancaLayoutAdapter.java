package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.MudancaLayoutActivity1;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ItemMudancaLayoutBinding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.TransactionUtils;

/**
 * Created by rpires on 27/01/2017.
 */

public class MudancaLayoutAdapter extends RecyclerView.Adapter<MudancaLayoutAdapter.MudancaLayoutViewHolder> {

    private final Activity activity;
    private List<ExemploCor> list;

    public MudancaLayoutAdapter(Activity activity, List<ExemploCor> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MudancaLayoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMudancaLayoutBinding binding = ItemMudancaLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MudancaLayoutViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final MudancaLayoutViewHolder holder, int position) {
        final ExemploCor sample = list.get(holder.getAdapterPosition());
        holder.binding.setSample(sample);
        holder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        Pair pais = new Pair(holder.binding.sampleIcon, "fab");
                        TransactionUtils.transitionToActivity(activity, MudancaLayoutActivity1.class, sample, pais);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MudancaLayoutViewHolder extends RecyclerView.ViewHolder {

        final ItemMudancaLayoutBinding binding;

        public MudancaLayoutViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
