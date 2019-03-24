package com.example.praja.myblog;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    TextView pro_description,pro_price,pro_name;
    ImageView pro_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatBtn;
    ElegantNumberButton elegantNumberButton;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        elegantNumberButton =(ElegantNumberButton)view.findViewById(R.id.number_button);
        pro_image =(ImageView)view.findViewById(R.id.img_ele);
        pro_name=(TextView)view.findViewById(R.id.proName);
        pro_description=(TextView)view.findViewById(R.id.description);
        pro_price=(TextView)view.findViewById(R.id.product_price);
        floatBtn =(FloatingActionButton)view.findViewById(R.id.btncart);
        collapsingToolbarLayout =(CollapsingToolbarLayout)view.findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpendedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapseAppBar);
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

}
