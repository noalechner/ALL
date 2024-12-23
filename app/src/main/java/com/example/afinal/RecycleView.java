import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerCustomAdapter extends RecyclerView.Adapter<RecyclerCustomAdapter.ViewHolder> {

    public ArrayList<Fruit> localDataset;

    public RecyclerCustomAdapter(ArrayList<Fruit> dataset) {
        localDataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View contactView=inflater.inflate(R.layout.my_custom_list,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(localDataset.get(position).getFruitName());
        viewHolder.tvWeight.setText(""+localDataset.get(position).getFruitWeight());
        viewHolder.ivCustom.setImageResource(localDataset.get(position).getFruitImageId());
        if(position%2==0)
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#4CAF50"));
        else
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#FFC8E4A9"));

        viewHolder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return localDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public final TextView tvName;
        public final TextView tvWeight;
        public final ImageView ivCustom;
        public final LinearLayout linearLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            view.setOnClickListener(this::select);
            view.setOnLongClickListener(this);
            tvName=view.findViewById(R.id.tvName);
            tvWeight=view.findViewById(R.id.tvWeight);
            ivCustom=view.findViewById(R.id.ivCustom);
            linearLayout=view.findViewById(R.id.layout);
        }

        private void select(View view) {
            Toast.makeText(itemView.getContext(), "Click "+ getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(itemView.getContext(), "Item "+ (getAdapterPosition()+1)+" removed", Toast.LENGTH_SHORT).show();
            localDataset.remove(getAdapterPosition());
            notifyDataSetChanged();
            return true;
        }
    }
}