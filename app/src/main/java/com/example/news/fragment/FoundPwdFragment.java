package com.example.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.news.R;
import com.example.news.control.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 权威 on 2016/12/27.
 */

public class FoundPwdFragment extends Fragment {
    @Bind(R.id.img_found_fragment_left)
    ImageView imgFoundFragmentLeft;
    @Bind(R.id.img_found_fragment_right)
    ImageView imgFoundFragmentRight;
    @Bind(R.id.edt_email_found)
    EditText edtEmailFound;
    @Bind(R.id.btn_found_fragment)
    Button btnFoundFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.found_pwd_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClick(getView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.img_found_fragment_left, R.id.img_found_fragment_right, R.id.btn_found_fragment})
    public void onClick(View view) {
        MainActivity activity= (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.img_found_fragment_left:
               activity.slidMenu.showMenu();
                break;
            case R.id.img_found_fragment_right:
                activity.slidMenu.showSecondaryMenu();
                break;
            case R.id.btn_found_fragment:
                break;
        }
    }
}
