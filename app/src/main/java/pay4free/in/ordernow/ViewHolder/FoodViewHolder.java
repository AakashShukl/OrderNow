package pay4free.in.ordernow.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pay4free.in.ordernow.Interface.ItemClickListener;
import pay4free.in.ordernow.R;

/**
 * Created by AAKASH on 10-10-2017.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView foodname;
    public ImageView foodimage;
    private ItemClickListener itemClickListener;

    public FoodViewHolder(View itemView) {
        super(itemView);

        foodname=(TextView)itemView.findViewById(R.id.food_name);
        foodimage=(ImageView)itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
