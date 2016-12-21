package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

/**
 * Created by rpires on 21/12/2016.
 */

public class ExemploCorAdapter extends RecyclerView.Adapter<ExemploCorAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private List<ExemploCor> list;

    public ExemploCorAdapter(Context context, List<ExemploCor> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_exemplo_cor, viewGroup, false);
        ViewHolder vw = new ViewHolder(view);
        return vw;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getImageView().setImageResource(list.get(position).getColor());
        holder.getTextView().setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imagem_cor);
            textView = (TextView) itemView.findViewById(R.id.txt_nome);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
