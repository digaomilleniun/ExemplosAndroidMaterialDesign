package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.domain.Carro;
import animation.rpires.com.br.exemplosmaterialdesign.domain.RecyclerViewOnClickListener;

/**
 * Created by rpires on 14/12/2016.
 */

public class CarroCardViewAdapter extends RecyclerView.Adapter<CarroViewHolder>{

    private List<Carro> listCarro;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;//Umas das formas de gerar envendo de click na tela

    public CarroCardViewAdapter(Context context, List<Carro> list) {
        this.listCarro = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_carro_card_view, viewGroup, false);
        CarroViewHolder vh = new CarroViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CarroViewHolder holder, int position) {
        holder.getImagemCarro().setImageResource(listCarro.get(position).getFoto());
        holder.getTxtModelo().setText(listCarro.get(position).getModelo());
        holder.getTxtMarca().setText(listCarro.get(position).getMarca());

        //Adicionando animação na lista
        try {
            YoYo.with(Techniques.FadeInRight)
                    .duration(700)
                    .playOn(holder.itemView);
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return listCarro.size();
    }

    public void addItem(Carro carro, int position) {
        listCarro.add(carro);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        listCarro.remove(position);
        notifyItemRemoved(position);
    }

    //Umas das formas de gerar envendo de click na tela
    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener) {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

//    public class CarroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        public ImageView imagemCarro;
//        private TextView txtModelo;
//        private TextView txtMarca;
//
//        public CarroViewHolder(View itemView) {
//            super(itemView);
//            imagemCarro = (ImageView) itemView.findViewById(R.id.imagem_carro);
//            txtModelo = (TextView) itemView.findViewById(R.id.txt_modelo);
//            txtMarca = (TextView) itemView.findViewById(R.id.txt_marca);
//
//            itemView.setOnClickListener(this);
//        }
//
//        //Umas das formas de gerar envendo de click na tela
//        @Override
//        public void onClick(View view) {
//            if (recyclerViewOnClickListener != null) {
//                recyclerViewOnClickListener.onClickListener(view, getAdapterPosition());
//            }
//        }
//    }
}

