package animation.rpires.com.br.exemplosmaterialdesign.domain;

import android.view.View;

/**
 * Created by rpires on 15/12/2016.
 */
//Umas das formas de gerar envendo de click na tela
public interface RecyclerViewOnClickListener {

    public void onClickListener(View view, int position);

    public void onLongClickListener(View view, int position);
}
