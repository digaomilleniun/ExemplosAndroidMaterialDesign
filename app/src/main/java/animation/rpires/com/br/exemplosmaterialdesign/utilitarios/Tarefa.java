package animation.rpires.com.br.exemplosmaterialdesign.utilitarios;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import animation.rpires.com.br.exemplosmaterialdesign.R;

public abstract class Tarefa extends AsyncTask<Void, Void, Boolean> {

    public enum NivelEsperaTotal {
        MAXIMO(30000), MEDIO(20000), MINIMO(10000);
        private int milissegundos;
        NivelEsperaTotal(int milissegundos) { this.milissegundos = milissegundos; }
        public int getMilissegundos() {
            return milissegundos;
        }
    }

    public Tarefa() {
        getAtividade().setProgressDialog(new ProgressDialog(getAtividade(), R.style.ProgressBarStyle));
        getAtividade().getProgressDialog().setProgressStyle(ProgressDialog.STYLE_SPINNER);
        getAtividade().getProgressDialog().setTitle(getAtividade().getString(R.string.loading));
        getAtividade().getProgressDialog().setMessage(getMensagemBarraProcesso());
        if(getAtividade().getProgressDialog().getWindow() != null) {
            getAtividade().getProgressDialog().getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        }
        getAtividade().getProgressDialog().setIndeterminate(true);
        getAtividade().getProgressDialog().setCancelable(isBarraProcessoCancelavel());
    }

    @Override
    final protected Boolean doInBackground(Void... params) {
        try {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if(getAtividade() != null && getAtividade().getProgressDialog() != null) {
                        getAtividade().getProgressDialog().show();
                    }
                }
            });

            if(executarConteudoSegundoPlano()) {
                int tempoEsperaAtual = 0;
                int tempoIntervalo = 1000;
                while (tempoEsperaAtual < getNivelEsperaTotal().getMilissegundos() && !isFinalizarExecucaoSegundoPlano()) {
                    Thread.sleep(tempoIntervalo);
                    tempoEsperaAtual += tempoIntervalo;
                }
            }
        } catch (InterruptedException e) {
            return false;
        }
        return isRetornoExecucaoSegundoPlano();
    }

    @Override
    final protected void onPostExecute(final Boolean success) {
        executarConteudoAposSegundoPlano(success);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if(getAtividade() != null && getAtividade().getProgressDialog() != null) {
                    getAtividade().getProgressDialog().dismiss();
                }
            }
        });
    }

    @Override
    final protected void onCancelled() {
        executarConteudoInicioCancelamento();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if(getAtividade() != null && getAtividade().getProgressDialog() != null) {
                    getAtividade().getProgressDialog().dismiss();
                }
            }
        });
        super.onCancelled();
    }

    protected abstract EncerramentoActivity getAtividade();

    protected abstract boolean isBarraProcessoCancelavel();

    protected abstract String getMensagemBarraProcesso();

    protected abstract boolean executarConteudoSegundoPlano();

    protected abstract boolean isFinalizarExecucaoSegundoPlano();

    protected abstract boolean isRetornoExecucaoSegundoPlano();

    protected abstract void executarConteudoAposSegundoPlano(final Boolean sucesso);

    protected abstract void executarConteudoInicioCancelamento();

    protected abstract NivelEsperaTotal getNivelEsperaTotal();
}
