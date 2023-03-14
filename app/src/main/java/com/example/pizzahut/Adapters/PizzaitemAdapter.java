package com.example.pizzahut.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzahut.ConfirmOderActivity;
import com.example.pizzahut.PizzaModal;
import com.example.pizzahut.PizzaitemModal;
import com.example.pizzahut.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PizzaitemAdapter extends RecyclerView.Adapter<PizzaitemAdapter.ViewHolder> {

    ArrayList<PizzaModal> pizzalist;
    Context context;

    public PizzaitemAdapter(Context context) {
        pizzalist = new ArrayList<>();
        this.context = context;
    }

    public void add(PizzaModal pizzaModal) {
        pizzalist.add(pizzaModal);
        notifyDataSetChanged();
    }

    public void clear() {
        pizzalist.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pizzaitem_singlerow, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PizzaModal modal = pizzalist.get(position);

        holder.p_name.setText(modal.getPizzaname());
        holder.p_price.setText(modal.getPizzaprice());
        Picasso.get().load(modal.getImage()).placeholder(R.drawable.pizzaimage)
                .into(holder.pizzaimg);
        final int[] count = {1};

        holder.increasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count[0]++;
                holder.p_count.setText(String.valueOf(count[0]));
            }
        });

        holder.dcreasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count[0] != 1) {
                    count[0]--;
                    holder.p_count.setText(String.valueOf(count[0]));
                }
            }
        });

        holder.additemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent()
            }
        });

        holder.additemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ConfirmOderActivity.class);
                intent.putExtra("PName", modal.getPizzaname());
                intent.putExtra("PPrice", modal.getPizzaprice());
                intent.putExtra("quntity", holder.p_count.getText().toString());
                intent.putExtra("PIMAge", modal.getImage());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pizzalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView increasebtn, dcreasebtn, pizzaimg;
        TextView p_count, p_name, p_price;
        Button additemBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            increasebtn = itemView.findViewById(R.id.increse_Btn_card_id);
            dcreasebtn = itemView.findViewById(R.id.decrese_btnid);
            pizzaimg = itemView.findViewById(R.id.singlecardpizzaimg_id);
            p_name = itemView.findViewById(R.id.singlecardpizzaname_id);
            p_count = itemView.findViewById(R.id.countpizza_text_id);
            p_price = itemView.findViewById(R.id.card_cpizza_price_id);
            additemBtn = itemView.findViewById(R.id.additembtnsinglecard_id);
        }
    }
}
//                Bundle bundle = new Bundle();
//                bundle.putString("n", "nirav");
//                OrderconfirmFragment fragment = new OrderconfirmFragment();
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager=((AppCompatActivity)context).getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.customer_dashbord_fremelayout_id,fragment).commit();
