package com.example.pizzahut.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzahut.PizzaitemModal;
import com.example.pizzahut.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SalePizzaAdapter extends FirebaseRecyclerAdapter<PizzaitemModal,SalePizzaAdapter.SaleViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SalePizzaAdapter(@NonNull FirebaseRecyclerOptions<PizzaitemModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SaleViewHolder holder, int position, @NonNull PizzaitemModal model) {

        holder.email.setText(model.getCustomerEmail());
        holder.date.setText(model.getDateofBuy());
        holder.pqunty.setText(model.getPizzaQunty());
        holder.dis_price.setText(model.getDiscountPrice());
        holder.dis_item.setText(model.getPizzaDiscount());
        holder.total.setText(model.getGrandTotal());
        holder.pname.setText(model.getPizzaName());
    }

    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sale_singlerow,parent,false);

        return new SaleViewHolder(view);
    }

    public class SaleViewHolder extends RecyclerView.ViewHolder {

        TextView email,pname,pqunty,dis_item,dis_price,total,date;
        public SaleViewHolder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.useremail_id);
            pname = itemView.findViewById(R.id.p_nameID);
            pqunty = itemView.findViewById(R.id.p_quntyID);
            dis_item = itemView.findViewById(R.id.discoun_itemID);
            dis_price = itemView.findViewById(R.id.dis_PriceID);
            total = itemView.findViewById(R.id.total_ID);
            date = itemView.findViewById(R.id.date_ID);
        }
    }
}
