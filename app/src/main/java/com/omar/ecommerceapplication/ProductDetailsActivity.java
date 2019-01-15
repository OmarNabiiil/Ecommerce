package com.omar.ecommerceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.omar.ecommerceapplication.network.ImageRequester;
import com.omar.ecommerceapplication.network.ProductEntry;

public class ProductDetailsActivity extends AppCompatActivity {

    private ProductEntry product;
    private ImageRequester requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        requester = ImageRequester.getInstance();

        initViews();
    }

    private void initViews() {
        TextView productName = findViewById(R.id.title);
        TextView productPrice = findViewById(R.id.productPrice);
        NetworkImageView productImage = findViewById(R.id.imageProduct);

        Bundle b = getIntent().getExtras();

        product = (ProductEntry) b.getSerializable("product");

        productName.setText(product.title);
        productPrice.setText(product.price);
        requester.setImageFromUrl(productImage, product.url);
    }
}
