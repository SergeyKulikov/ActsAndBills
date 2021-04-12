package com.mycoloruniverse.actsbills.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.mycoloruniverse.actsbills.R;
import com.mycoloruniverse.actsbills.models.Company;
import com.mycoloruniverse.actsbills.models.CompanyProperty;
import com.mycoloruniverse.actsbills.models.ICompanyDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.RatesViewHolder> {
    private final List<Company> companyList = new ArrayList<>();

    public CompanyAdapter() {
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList.clear();
        this.companyList.addAll(companyList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CompanyAdapter.RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.company_item_layout, parent, false);
                       // inflate(this.layoutId, parent, false);

        return new CompanyAdapter.RatesViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.RatesViewHolder holder, int position) {
        // CompanyProperty companyProperty = companyPropertyList.get(activeFolder).get(position);

        holder.tvName.setText(companyList.get(position).getName());
        holder.tvInn.setText(companyList.get(position).getPropertyMap().get("inn"));
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    class RatesViewHolder extends ViewHolder {
        private final TextView tvName;
        private final TextView tvInn;

        @SuppressLint("CheckResult")
        public RatesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvInn = itemView.findViewById(R.id.tvINN);

            /*
            RxTextView.textChanges(etPropertyValue)
                    .subscribe(charSequence -> {
                        if (getAdapterPosition() != -1) {
                            companyPropertyList.get(activeFolder).get(getAdapterPosition())
                                    .setValue(String.valueOf(charSequence));
                        }
                    });
             */
        }
    }
}
