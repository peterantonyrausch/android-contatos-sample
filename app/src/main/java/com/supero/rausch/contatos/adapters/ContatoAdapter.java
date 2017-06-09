package com.supero.rausch.contatos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.supero.rausch.contatos.R;
import com.supero.rausch.contatos.models.Contato;

import java.util.List;

/**
 * Created by Rausch on 08/06/2017.
 */

public class ContatoAdapter extends BaseAdapter {
    private Context context;
    private List<Contato> contatos;
    private LayoutInflater inflater;

    public ContatoAdapter(Context context, List<Contato> contatos) {
        super();
        this.context = context;
        this.contatos = contatos;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void notifyDataSetChanged() {
        try {
            super.notifyDataSetChanged();
        } catch (Exception e) {
            trace("Erro: " + e.getMessage());
        }
    }

    private void trace(String msg) {
        toast(msg);
    }

    public void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    public void remove(final Contato contato) {
        this.contatos.remove(contato);
    }

    public void add(final Contato contato) {
        this.contatos.add(contato);
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            Contato contato = contatos.get(position);

            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listagem_contatos_linha, null);
                holder = new ViewHolder();
                holder.labelNome = (TextView) convertView.findViewById(R.id.labelNome);
                holder.labelTelefone = (TextView) convertView.findViewById(R.id.labelTelefone);
                holder.labelEmail = (TextView) convertView.findViewById(R.id.labelEmail);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.labelNome.setText(contato.getNome());
            holder.labelTelefone.setText(contato.getTelefone());
            holder.labelEmail.setText(contato.getEmail());

            return convertView;
        } catch (Exception e) {
            trace("Erro: " + e.getMessage());
        }

        return convertView;
    }

    static class ViewHolder {
        public TextView labelNome;
        public TextView labelTelefone;
        public TextView labelEmail;
    }
}
