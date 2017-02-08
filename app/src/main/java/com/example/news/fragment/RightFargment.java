package com.example.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.common.Constant;
import com.example.news.common.SharedPreferenceUser;
import com.example.news.control.MainActivity;
import com.example.news.control.UserCenterActivity;
import com.example.news.model.biz.UpdateManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 权威 on 2016/12/26.
 * 给右边的侧滑栏添加内容
 */

public class RightFargment extends Fragment {
    @Bind(R.id.img_regist_right_fragment)
    ImageView imgRegistRightFragment;
    @Bind(R.id.text_regist_right_fragment)
    TextView textRegistRightFragment;
    @Bind(R.id.img_weixin)
    ImageView imgWeixin;
    @Bind(R.id.img_weibo)
    ImageView imgWeibo;
    @Bind(R.id.img_friend_cricle)
    ImageView imgFriendCricle;
    @Bind(R.id.img_xinlang)
    ImageView imgXinlang;
    @Bind(R.id.text_version_update)
    TextView textVersionUpdate;
    MainActivity   mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sliding_right_fragment, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        String token= getActivity().getSharedPreferences(Constant.LOGO_tOKEN, Context.CONTEXT_INCLUDE_CODE).getString(Constant.LOGO_tOKEN_KEY,null);

        if(token!=null&&!token.equals(null)){

            String name=getActivity().getSharedPreferences(Constant.LOGO_tOKEN, Context.CONTEXT_INCLUDE_CODE).getString(Constant.USER_NAME_KEY,null);
            textRegistRightFragment.setText(name);

            String photo= getActivity().getSharedPreferences(Constant.LOGO_tOKEN, Context.CONTEXT_INCLUDE_CODE).getString(Constant.USER_PHOTO_KEY,null);
            RequestCreator load = Picasso.with(getActivity()).load(photo);
            load.resize(120,120);
            load.into(imgRegistRightFragment);
            imgRegistRightFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), UserCenterActivity.class);
               startActivity(intent);

                }

            });
            textRegistRightFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), UserCenterActivity.class);
                    startActivity(intent);

                }

            });
        }

         onClick(getView());
        }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.img_regist_right_fragment, R.id.text_regist_right_fragment, R.id.img_weixin, R.id.img_weibo, R.id.img_friend_cricle, R.id.img_xinlang, R.id.text_version_update})
    public void onClick(View view) {
        mainActivity= (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.img_regist_right_fragment:
               mainActivity.regesit();
                break;
            case R.id.text_regist_right_fragment:
                mainActivity.regesit();
                break;
            case R.id.img_weixin:
                break;
            case R.id.img_weibo:
                break;
            case R.id.img_friend_cricle:
                break;
            case R.id.img_xinlang:
                break;
            case R.id.text_version_update:

                UpdateManager updateManager=new UpdateManager(getContext());
                updateManager.isUpdate();
                break;
        }
    }
}
