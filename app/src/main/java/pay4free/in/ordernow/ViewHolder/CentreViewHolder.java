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

public class CentreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView centrename;
    public ImageView centreimage;
    private ItemClickListener itemClickListener;

    public CentreViewHolder(View itemView) {
        super(itemView);

        centrename=(TextView)itemView.findViewById(R.id.centre_name);
        centreimage=(ImageView)itemView.findViewById(R.id.centre_image);

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
