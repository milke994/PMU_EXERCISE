package com.example.android.podsetnik;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MojAdapter extends RecyclerView.Adapter<MojAdapter.MojHolder> {

    private ArrayList<Podsetnik> mPodsetnici;
    private Context mContext;

    public MojAdapter(Context mContext, ArrayList<Podsetnik> mPodsetnici) {
        this.mContext = mContext;
        this.mPodsetnici = mPodsetnici;
    }

    @NonNull
    @Override
    public MojHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.moj_holder, viewGroup, false);
        return new MojHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MojHolder mojHolder, int position) {
        Podsetnik trenutniPodsetnik = mPodsetnici.get(position);
        mojHolder.postavi(trenutniPodsetnik);
    }

    @Override
    public int getItemCount() {
        return mPodsetnici.size();
    }

    class MojHolder extends RecyclerView.ViewHolder {

        private TextView mNaziv;
        private TextView mDatum;

        public MojHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent eksplicitniIntent = new Intent(mContext, DetailActivity.class);
                    eksplicitniIntent.putExtra("naziv", mNaziv.getText().toString());
                    eksplicitniIntent.putExtra("datum", mDatum.getText().toString());
                    mContext.startActivity(eksplicitniIntent);
                }

            });

            mNaziv = itemView.findViewById(R.id.moj_holder_naziv_textview);
            mDatum = itemView.findViewById(R.id.moj_holder_datum_textview);
        }

        public void postavi(Podsetnik podsetnik) {
            mNaziv.setText(podsetnik.getNaziv());
            mDatum.setText(podsetnik.getDatum());
        }
    }
}
