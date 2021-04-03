package com.mycoloruniverse.actsbills.view;

import android.annotation.SuppressLint;
import android.icu.util.Currency;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.mycoloruniverse.actsbills.R;
import com.mycoloruniverse.actsbills.models.CompanyProperty;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.RatesViewHolder> {
    private final List<CompanyProperty> companyPropertyList = new ArrayList<>();
    private int layoutId;

    public CompanyDetailsAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public CompanyDetailsAdapter.RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                //inflate(R.layout.company_property_item_layout, parent, false);
                inflate(this.layoutId, parent, false);

        return new CompanyDetailsAdapter.RatesViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull CompanyDetailsAdapter.RatesViewHolder holder, int position) {
        CompanyProperty companyProperty = companyPropertyList.get(position);

        holder.tvPropertyName.setText(companyProperty.getCaption());
        holder.etPropertyValue.setText(companyProperty.getValue() == null ? companyProperty.getDefault_value() : companyProperty.getValue());
        // holder.btnPropertySelect.setText("...");
    }

    @Override
    public int getItemCount() {
        return companyPropertyList.size();
    }

    public void setCompanyPropertyList(List<CompanyProperty> companyPropertyList) {
        if (companyPropertyList != null) {
            this.companyPropertyList.clear();
            this.companyPropertyList.addAll(companyPropertyList);
            this.notifyDataSetChanged();
        }
    }

    class RatesViewHolder extends ViewHolder {
        private final TextView tvPropertyName;
        private final EditText etPropertyValue;
        private final ImageButton btnPropertySelect;

        public RatesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
            etPropertyValue = itemView.findViewById(R.id.etPropertyValue);
            btnPropertySelect = itemView.findViewById(R.id.btnPropertySelect);

            etPropertyValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    companyPropertyList.get(
                            getAdapterPosition()).setValue(etPropertyValue.getText().toString()
                    );
                }
            });

        }
    }
}
