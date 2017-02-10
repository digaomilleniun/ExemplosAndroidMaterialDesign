package animation.rpires.com.br.exemplosmaterialdesign.utilitarios;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import animation.rpires.com.br.exemplosmaterialdesign.R;

public class ConfirmacaoDialog extends AlertDialog {

    private String titulo;
    private String mensagem;
    private Activity activity;
    private OnBotaoListener listener;

    public ConfirmacaoDialog(Activity activity, String mensagem, final OnBotaoListener listener) {
        super(activity);
        this.activity = activity;
        this.mensagem = mensagem;
        this.listener = listener;
    }

    public ConfirmacaoDialog(Activity activity, String titulo, String mensagem, final OnBotaoListener listener) {
        super(activity);
        this.activity = activity;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.listener = listener;
    }

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.dialog_mensagem_confirmacao_alert);
//
//        if(titulo != null) {
//            TextView txtTitulo = (TextView) findViewById(R.id.txt_titulo);
//            txtTitulo.setText(titulo);
//        }
//
//        if(mensagem != null) {
//            TextView txtMensagem = (TextView) findViewById(R.id.txt_mensagem);
//            txtMensagem.setText(mensagem);
//        }

        if(listener != null) {

//            Button btn1 = (Button) findViewById(R.id.btn_1);
//            btn1.setText(listener.getTextBotao1() != null ? listener.getTextBotao1() : "");
//            btn1.setVisibility(listener.isVisivelBotao1() ? View.VISIBLE : View.GONE);
//            btn1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ConfirmacaoDialog.this.dismiss();
//                    listener.OnClickBotao1();
//                }
//            });
//
//            Button btn2 = (Button) findViewById(R.id.btn_2);
//            btn2.setText(listener.getTextBotao2() != null ? listener.getTextBotao2() : "");
//            btn2.setVisibility(listener.isVisivelBotao2() ? View.VISIBLE : View.GONE);
//            btn2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ConfirmacaoDialog.this.dismiss();
//                    listener.OnClickBotao2();
//                }
//            });
//
//            Button btn3 = (Button) findViewById(R.id.btn_3);
//            btn3.setText(listener.getTextBotao3() != null ? listener.getTextBotao3() : "");
//            btn3.setVisibility(listener.isVisivelBotao3() ? View.VISIBLE : View.GONE);
//            btn3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ConfirmacaoDialog.this.dismiss();
//                    listener.OnClickBotao3();
//                }
//            });

        }
    }

    public interface OnBotaoListener {
        boolean isVisivelBotao1();
        String getTextBotao1();
        void OnClickBotao1();
        boolean isVisivelBotao2();
        String getTextBotao2();
        void OnClickBotao2();
        boolean isVisivelBotao3();
        String getTextBotao3();
        void OnClickBotao3();
    }
}
