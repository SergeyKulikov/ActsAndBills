package com.mycoloruniverse.actsbills.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.mycoloruniverse.actsbills.R;
import com.mycoloruniverse.actsbills.models.Catalog;
import com.mycoloruniverse.actsbills.models.CatalogField;
import com.mycoloruniverse.actsbills.models.CompanyProperty;
import com.mycoloruniverse.actsbills.models.ICompanyDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.RatesViewHolder> implements ICompanyDetails {
    private final Catalog catalog = new Catalog();
    private final Map<String, List<CompanyProperty>> companyPropertyList = new HashMap<>();
    // private final List<Observable<CharSequence>> observables = new ArrayList<>();
    private int layoutId;
    private String activeFolder;
    private final Map<String, Integer> activePosition = new HashMap<>();

    public CatalogAdapter(int layoutId, JSONObject object, CatalogField[] catalogField) throws JSONException {
        this.layoutId = layoutId;

        this.catalog.clearFields();
        for (CatalogField field : catalogField) {
            this.catalog.addField(field);
        }

        /*
        JSONArray jsonArray = object.getJSONArray("catalog");
        for (int a = 0; a < jsonArray.length(); a++) {
            JSONObject jsonRow = (JSONObject) jsonArray.get(a);
            Log.d("TAG", jsonRow.toString());

            for (int f = 0; f < jsonRow.length(); f++) {

                // d = jsonRow.getAsJsonArray().getAsString();
            }
        }

         */
    }

    @NonNull
    @Override
    public CatalogAdapter.RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                //inflate(R.layout.company_property_item_layout, parent, false);
                        inflate(this.layoutId, parent, false);

        return new CatalogAdapter.RatesViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.RatesViewHolder holder, int position) {
        // CompanyProperty companyProperty = companyPropertyList.get(activeFolder).get(position);

        // holder.tvPropertyName.setText(companyProperty.getCaption());
        // holder.etPropertyValue.setText(companyProperty.getValue() == null ? companyProperty.getDefault_value() : companyProperty.getValue());
        // holder.btnPropertySelect.setText("...");
    }

    @Override
    public int getItemCount() {
        if (companyPropertyList.containsKey(activeFolder)) {
            return companyPropertyList.get(activeFolder).size();
        }
        return 0;
    }

    public void setCompanyPropertyList(Map<String, List<CompanyProperty>> companyPropertyList) {
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
        // private final TextView tvPropertyName;
        // private final EditText etPropertyValue;
        // private final ImageButton btnPropertySelect;

        @SuppressLint("CheckResult")
        public RatesViewHolder(@NonNull View itemView) {
            super(itemView);

            LinearLayout ll = itemView.findViewById(R.id.table_row);

            TextView []cols = new TextView[catalog.getFieldCount()];

            for (int f=0; f<catalog.getFieldCount(); f++) {
                TextView textView = new TextView(itemView.getContext());

                TextView newTextView = new TextView(itemView.getContext());
                newTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                // newTextView.setTextColor(Color.parseColor("#ffffff"));
                // newTextView.setText("123456");
                newTextView.setText( String.valueOf(catalog.getValue(0, catalog.getFieldByIndex(f).getId())) );

                ll.addView(newTextView);
            }

            // tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
            // etPropertyValue = itemView.findViewById(R.id.etPropertyValue);
            // btnPropertySelect = itemView.findViewById(R.id.btnPropertySelect);



            /*
            for (CatalogField field : catalogField) {
                this.catalog.addField(field);
            }





            RxTextView.textChanges(etPropertyValue)
                    .subscribe(charSequence -> {
                        if (getAdapterPosition() != -1) {
                            companyPropertyList.get(activeFolder).get(getAdapterPosition())
                                    .setValue(String.valueOf(charSequence));
                        }
                    });*/
        }
    }
}
