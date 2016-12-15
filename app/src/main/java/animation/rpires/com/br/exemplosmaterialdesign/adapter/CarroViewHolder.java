package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import animation.rpires.com.br.exemplosmaterialdesign.R;

/**
 * Created by rpires on 15/12/2016.
 */

public class CarroViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagemCarro;
        private TextView txtModelo;
        private TextView txtMarca;

        public CarroViewHolder(View itemView) {
            super(itemView);
            imagemCarro = (ImageView) itemView.findViewById(R.id.imagem_carro);
            txtModelo = (TextView) itemView.findViewById(R.id.txt_modelo);
            txtMarca = (TextView) itemView.findViewById(R.id.txt_marca);
        }

    public ImageView getImagemCarro() {
        return imagemCarro;
    }

    public void setImagemCarro(ImageView imagemCarro) {
        this.imagemCarro = imagemCarro;
    }

    public TextView getTxtModelo() {
        return txtModelo;
    }

    public void setTxtModelo(TextView txtModelo) {
        this.txtModelo = txtModelo;
    }

    public TextView getTxtMarca() {
        return txtMarca;
    }

    public void setTxtMarca(TextView txtMarca) {
        this.txtMarca = txtMarca;
    }
}
