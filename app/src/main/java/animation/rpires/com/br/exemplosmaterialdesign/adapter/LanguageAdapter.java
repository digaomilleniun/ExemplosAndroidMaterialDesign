package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by rpires on 20/12/2016.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder>{

    private Locale[] mLocales;

    public LanguageAdapter(Locale[] availableLocales) {
        mLocales = availableLocales;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mLocales[position].getDisplayName());
    }

    @Override
    public int getItemCount() {
        return mLocales.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}
