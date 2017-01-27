package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.AnimationsActivity1;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.RevealActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.SharedElementActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.TransitionActivity1;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ItemExemploCorBinding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.TransitionHelper;

/**
 * Created by rpires on 21/12/2016.
 */

public class ExemploCorAdapter extends RecyclerView.Adapter<ExemploCorAdapter.ViewHolder>{

    //private LayoutInflater layoutInflater;
    private final Activity activity;
    private List<ExemploCor> list;

    public ExemploCorAdapter(Activity activity, List<ExemploCor> list) {
        this.activity = activity;
        this.list = list;
        //layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        View view = layoutInflater.inflate(R.layout.item_exemplo_cor, viewGroup, false);
//        ViewHolder vw = new ViewHolder(view);
//        return vw;
        ItemExemploCorBinding binding = ItemExemploCorBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final ExemploCor sample = list.get(viewHolder.getAdapterPosition());
        viewHolder.binding.setSample(sample);
        viewHolder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (viewHolder.getAdapterPosition()) {
                    case 0:
                        transitionToActivity(TransitionActivity1.class, sample);
                        break;
                    case 1:
                        transitionToActivity(SharedElementActivity.class, viewHolder, sample);
                        break;
                    case 2:
                        transitionToActivity(AnimationsActivity1.class, sample);
                        break;
                    case 3:
                        transitionToActivity(RevealActivity.class, viewHolder, sample, R.string.transition_reveal1);
                        break;
                }
            }
        });
//        holder.getImageView().setImageResource(list.get(position).getColor());
//        holder.getTextView().setText(list.get(position).getName());
    }

    private void transitionToActivity(Class target, ExemploCor sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true);
        startActivity(target, pairs, sample);
    }

    private void transitionToActivity(Class target, ViewHolder viewHolder, ExemploCor sample, int transitionName) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(viewHolder.binding.sampleIcon, activity.getString(transitionName)));
        startActivity(target, pairs, sample);
    }

    private void transitionToActivity(Class target, ViewHolder viewHolder, ExemploCor sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(viewHolder.binding.sampleIcon, activity.getString(R.string.square_blue_name)),
                new Pair<>(viewHolder.binding.sampleName, activity.getString(R.string.sample_blue_title)));
        startActivity(target, pairs, sample);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, ExemploCor sample) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("sample", sample);
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ItemExemploCorBinding binding;

//        public ImageView imageView;
//        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
//            imageView = (ImageView) itemView.findViewById(R.id.imagem_cor);
//            textView = (TextView) itemView.findViewById(R.id.txt_nome);
        }

//        public ImageView getImageView() {
//            return imageView;
//        }
//
//        public void setImageView(ImageView imageView) {
//            this.imageView = imageView;
//        }
//
//        public TextView getTextView() {
//            return textView;
//        }
//
//        public void setTextView(TextView textView) {
//            this.textView = textView;
//        }

        public ItemExemploCorBinding getBinding() {
            return binding;
        }
    }
}
