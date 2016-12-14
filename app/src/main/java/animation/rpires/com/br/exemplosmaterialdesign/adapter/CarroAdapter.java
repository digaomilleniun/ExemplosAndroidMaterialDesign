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
import animation.rpires.com.br.exemplosmaterialdesign.domain.Carro;

/**
 * Created by rpires on 14/12/2016.
 */

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarroViewHolder>{

    private List<Carro> listCarro;
    private LayoutInflater layoutInflater;

    public CarroAdapter(Context context, List<Carro> list) {
        this.listCarro = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_carro, viewGroup, false);
        CarroViewHolder vh = new CarroViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CarroViewHolder holder, int position) {
        holder.imagemCarro.setImageResource(listCarro.get(position).getFoto());
        holder.txtModelo.setText(listCarro.get(position).getModelo());
        holder.txtMarca.setText(listCarro.get(position).getMarca());
    }

    @Override
    public int getItemCount() {
        return listCarro.size();
    }

    public void addItem(Carro carro, int position) {
        listCarro.add(carro);
        notifyItemInserted(position);
    }

    public class CarroViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagemCarro;
        private TextView txtModelo;
        private TextView txtMarca;

        public CarroViewHolder(View itemView) {
            super(itemView);
            imagemCarro = (ImageView) itemView.findViewById(R.id.imagem_carro);
            txtModelo = (TextView) itemView.findViewById(R.id.txt_modelo);
            txtMarca = (TextView) itemView.findViewById(R.id.txt_marca);
        }
    }
}

