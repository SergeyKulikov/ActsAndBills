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
import com.mycoloruniverse.actsbills.models.CompanyProperty;
import com.mycoloruniverse.actsbills.models.ICompanyDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.RatesViewHolder> implements ICompanyDetails {
    private final Map<String, List<CompanyProperty>> companyPropertyList = new HashMap<>();
    // private final List<Observable<CharSequence>> observables = new ArrayList<>();
    private int layoutId;
    private String activeFolder;
    private final Map<String, Integer> activePosition = new HashMap<>();

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
        CompanyProperty companyProperty = companyPropertyList.get(activeFolder).get(position);

        holder.tvPropertyName.setText(companyProperty.getCaption());
        holder.etPropertyValue.setText(companyProperty.getValue() == null ? companyProperty.getDefault_value() : companyProperty.getValue());
        // holder.btnPropertySelect.setText("...");
    }

    @Override
    public int getItemCount() {
        if (companyPropertyList.containsKey(activeFolder)) {
            return companyPropertyList.get(activeFolder).size();
        }
        return 0;
    }

    public void setCompanyPropertyMap(Map<String, List<CompanyProperty>> companyPropertyList) {
        if (companyPropertyList != null) {
            this.companyPropertyList.clear();
            this.companyPropertyList.putAll(companyPropertyList);
            this.notifyDataSetChanged();
        }
    }

    public void setActiveFolder(String activeFolder) {
        this.activeFolder = activeFolder;
        notifyDataSetChanged();
    }

    public Map<String, List<CompanyProperty>> getCompanyPropertyList() {
        return companyPropertyList;
    }

    @Override
    public void TestingFunction() {
        Log.d("TAG", "It's a superclass");
    }

    class RatesViewHolder extends ViewHolder {
        private final TextView tvPropertyName;
        private final EditText etPropertyValue;
        private final ImageButton btnPropertySelect;

        @SuppressLint("CheckResult")
        public RatesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
            etPropertyValue = itemView.findViewById(R.id.etPropertyValue);
            btnPropertySelect = itemView.findViewById(R.id.btnPropertySelect);

            RxTextView.textChanges(etPropertyValue)
                    .subscribe(charSequence -> {
                        if (getAdapterPosition() != -1) {
                            companyPropertyList.get(activeFolder).get(getAdapterPosition())
                                    .setValue(String.valueOf(charSequence));
                        }
                    });
        }
    }
}
