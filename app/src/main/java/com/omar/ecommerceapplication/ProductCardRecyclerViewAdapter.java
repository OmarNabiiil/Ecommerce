package com.omar.ecommerceapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.omar.ecommerceapplication.network.ImageRequester;
import com.omar.ecommerceapplication.network.ProductEntry;

import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardRecyclerViewAdapter.ProductCardViewHolder> {

    private List<ProductEntry> productList;
    private ImageRequester imageRequester;
    private IProductClickListener monClickListener;

    public interface IProductClickListener {
        void onProductClickListener(RecyclerView.ViewHolder viewHolder, int itemPosition);
    }

    ProductCardRecyclerViewAdapter(List<ProductEntry> productList, IProductClickListener listener) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
        monClickListener = listener;
    }

    public class ProductCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public NetworkImageView productImage;
        public TextView productTitle;
        public TextView productPrice;

        public ProductCardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
        }

        @Override
        public void onClick(View v) {
            monClickListener.onProductClickListener(this, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            imageRequester.setImageFromUrl(holder.productImage, product.url);

        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
