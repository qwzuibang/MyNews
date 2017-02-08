package com.example.news.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.common.Constant;
import com.example.news.common.SharedPreferenceUser;
import com.example.news.control.MainActivity;
import com.example.news.control.UserCenterActivity;
import com.example.news.model.entity.Regiest;
import com.example.news.presenter.NewsPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 权威 on 2016/12/27.
 * 登录界面
 */

public class RegiestFragment extends Fragment {
    @Bind(R.id.left_slide_regiest)
    ImageView leftSlideRegiest;
    @Bind(R.id.rigth_slide_regeist)
    ImageView rigthSlideRegeist;
    @Bind(R.id.edt_user_reg)
    EditText edtUserReg;
    @Bind(R.id.edt_psw_reg)
    EditText edtPswReg;
    @Bind(R.id.btn_fragment_regist_enroll)
    Button btnFragmentRegistEnroll;
    @Bind(R.id.btn_fragment_regist_found_pwd)
    Button btnFragmentRegistFoundPwd;
    @Bind(R.id.btn_fragment_regist_logo)
    Button btnFragmentRegistLogo;
    protected  String uid;
    protected  String pwd;
    protected  String  userPutCorrect="[a-zA-Z0-9]{3,16}";
    protected  String pwdPutCorrect="[^\\s]{6,16}";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regiest_fragment, null);
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
    MainActivity activity;
    @OnClick({R.id.left_slide_regiest, R.id.rigth_slide_regeist, R.id.btn_fragment_regist_enroll, R.id.btn_fragment_regist_found_pwd, R.id.btn_fragment_regist_logo})
    public void onClick(View view) {
         activity= (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.left_slide_regiest:
                //显示左边侧滑栏
               activity.slidMenu.showMenu();
                break;
            case R.id.rigth_slide_regeist:
                //显示右边侧滑栏
                activity.slidMenu.showSecondaryMenu();
                break;
            case R.id.btn_fragment_regist_enroll:
                //显示注册界面
                activity.enroll();
                break;
            case R.id.btn_fragment_regist_found_pwd:
                //显示找回密码界面
                activity.foundPwd();
                break;
            case R.id.btn_fragment_regist_logo:
                //获取编辑框的内容
          uid=edtUserReg.getText().toString().trim();
          pwd=edtPswReg.getText().toString().trim();
                //拼接地址
                StringBuffer buf=new StringBuffer();
           buf.append(Constant.REGEIST_PATH).append("uid").append("=").append(uid)
                   .append("&").append("pwd").append("=").append(pwd).append("&")
                   .append("device").append("=").append("0");
                if(Pattern.matches(userPutCorrect,uid)){
                    if(Pattern.matches(pwdPutCorrect,pwd)){
                        NewsPresenter presenter=new NewsPresenter(buf.toString(), getActivity(), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                             //响应成功
                                Gson  gson=new Gson();
                       Regiest regiests=gson.fromJson(jsonObject.toString(),new TypeToken<Regiest>(){}.getType());
                             String massage=regiests.getMessage();


                          //      Log.e("qwqwqwqwqwq",regiests.getData().getExplain());

                                if(massage.equals("OK")){
                                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                                    SharedPreferenceUser.getSharedPreference(getActivity(),Constant.LOGO_tOKEN).saveLogoSharedpreference(Constant.LOGO_tOKEN_KEY,regiests.getData().getToken());

                                    Intent intent=new Intent(RegiestFragment.this.getActivity(), UserCenterActivity.class);
                                    startActivity(intent);


                                }
                                else{
                                    Toast.makeText(getActivity(),regiests.getData().getExplain(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                //错误响应
                                Toast.makeText(getActivity(), "Net Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                        );
                        presenter.requestHttp();


                    }else{
                        Toast.makeText(getActivity(), "密码格式有误", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getActivity(), "用户名格式有误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
