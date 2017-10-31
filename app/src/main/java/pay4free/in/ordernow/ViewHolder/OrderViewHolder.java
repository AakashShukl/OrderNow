package pay4free.in.ordernow.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pay4free.in.ordernow.Interface.ItemClickListener;
import pay4free.in.ordernow.R;

/**
 * Created by AAKASH on 14-10-2017.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView OrderId,Orderstatus,orderphone,orderAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        OrderId=(TextView) itemView.findViewById(R.id.order_id);
        Orderstatus=(TextView)itemView.findViewById(R.id.order_status);
        orderphone=(TextView) itemView.findViewById(R.id.order_phone);
        orderAddress=(TextView)itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
