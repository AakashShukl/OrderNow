package pay4free.in.ordernow.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pay4free.in.ordernow.Interface.ItemClickListener;
import pay4free.in.ordernow.Model.Order;
import pay4free.in.ordernow.R;

/**
 * Created by AAKASH on 12-10-2017.
 */
class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
public TextView txt_cart_name,txt_price;
    public ImageView img_cart_count;
    private ItemClickListener itemClickListener;

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_cart_name=(TextView)itemView.findViewById(R.id.cart_item_name);
        txt_price=(TextView)itemView.findViewById(R.id.cart_item_Price);
        img_cart_count=(ImageView)itemView.findViewById(R.id.cart_item_count);


    }

    public TextView getTxt_cart_name() {
        return txt_cart_name;
    }

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public TextView getTxt_price() {
        return txt_price;
    }

    public void setTxt_price(TextView txt_price) {
        this.txt_price = txt_price;
    }

    public ImageView getImg_cart_count() {
        return img_cart_count;
    }

    public void setImg_cart_count(ImageView img_cart_count) {
        this.img_cart_count = img_cart_count;
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public void onClick(View v) {

    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> listdata=new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        TextDrawable drawable=TextDrawable.builder().buildRound(""+listdata.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);
        Locale locale=new Locale("en","IN");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
       int price=(Integer.parseInt(listdata.get(position).getPrice()))*(Integer.parseInt(listdata.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));
        holder.txt_cart_name.setText(listdata.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
}
