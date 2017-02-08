package com.example.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.common.Constant;
import com.example.news.control.MainActivity;
import com.example.news.model.entity.Enralls;
import com.example.news.presenter.NewsPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.regex.Pattern;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 权威 on 2016/12/27.
 */

public class EnrallFargment extends Fragment {
    @Bind(R.id.img_enroll_fragment_left)
    ImageView imgEnrollFragmentLeft;
    @Bind(R.id.img_enroll_fragment_right)
    ImageView imgEnrollFragmentRight;
    @Bind(R.id.edt_id_enr)
    EditText edtIdEnr;
    @Bind(R.id.edt_user_enr)
    EditText edtUserEnr;
    @Bind(R.id.edt_psw_enr)
    EditText edtPswEnr;
    @Bind(R.id.btn_fragment_enroll)
    Button btnFragmentEnroll;
    @Bind(R.id.check_fragment_enroll)
    CheckBox checkFragmentEnroll;



    protected String version = "1";
    protected String email ;
    protected String uid ;
    protected String pwd ;
    //分别定义邮箱，用户名和密码的正则表达式的格式
    protected  String  emailPutCorrect="([a-zA-Z0-9])+\\@(([a-zA-Z0-9])+\\.)+([a-zA-Z0-9]){2,5}";
    protected  String  userPutCorrect="[a-zA-Z0-9]{3,16}";
    protected  String pwdPutCorrect="[^\\s]{6,16}";
    MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enroll_fragment, null);
        ButterKnife.bind(this, view);


        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onClick(getView());
        checkFragmentEnroll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkFragmentEnroll.setChecked(b? true : false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //设置注册页面的点击事件
    @OnClick({R.id.img_enroll_fragment_left, R.id.img_enroll_fragment_right, R.id.btn_fragment_enroll, R.id.check_fragment_enroll})
    public void onClick(View view) {
         activity= (MainActivity) getActivity();

        switch (view.getId()) {
            case R.id.img_enroll_fragment_left:
                //左边侧滑栏
                activity.slidMenu.showMenu();
                break;
            case R.id.img_enroll_fragment_right:
                //右边侧滑栏
                activity.slidMenu.showSecondaryMenu();
                break;

            case R.id.btn_fragment_enroll:

                String entrolPath = this.getEnrolPath().toString();

                Log.e("sadsdfsadffd",entrolPath);
                Log.e("asadadsad",email);
             if(checkFragmentEnroll.isChecked()){
                 if(Pattern.matches(emailPutCorrect,email)){
                     if(Pattern.matches(userPutCorrect,uid)){
                         if(Pattern.matches(pwdPutCorrect,pwd)){
                 //发送注册网络请求
              NewsPresenter  presenter=new NewsPresenter(entrolPath, getActivity(), new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject jsonObject) {
                      //正确响应并数据解析
                      Gson gson=new Gson();
                      Enralls enralls=gson.fromJson(jsonObject.toString(),new TypeToken<Enralls>(){}.getType());
                         //显示结果
                     // String explain=enralls.getData().getExplain();
                      String massage=enralls.getMessage();

                     if(massage.equals("OK")){
                         Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                         activity.regesit();
                     }
                      else{
                         Toast.makeText(getActivity(),enralls.getData().getExplain(), Toast.LENGTH_SHORT).show();
                     }

                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError volleyError) {
                      //错误响应
                      Toast.makeText(getActivity(), "net error", Toast.LENGTH_SHORT).show();
                  }
              });
                             presenter.requestHttp();


                         }else{
                             Toast.makeText(getActivity(), "密码格式错误", Toast.LENGTH_SHORT).show();
                         }
                     }else{
                         Toast.makeText(getActivity(), "用户名格式输入错误", Toast.LENGTH_SHORT).show();
                     }
                 }
                 else{
                     Toast.makeText(getActivity(), "邮箱格式输入错误", Toast.LENGTH_SHORT).show();
                 }
             }
                else{
                 Toast.makeText(getActivity(), "没有同意协议条款", Toast.LENGTH_SHORT).show();
             }
                break;

            case R.id.check_fragment_enroll:

                break;
        }
    }
    //拼接注册路径
    public StringBuffer getEnrolPath() {
        email = edtIdEnr.getText().toString();
        uid = edtUserEnr.getText().toString();
        pwd = edtPswEnr.getText().toString();
        Map<String, String> enrolMap = new HashMap<String, String>();
        enrolMap.put("email", email);
        enrolMap.put("uid", uid);

        enrolMap.put("pwd", pwd);
        StringBuffer str = new StringBuffer();
        str.append(Constant.ENROLL_PATH);
        Set<Map.Entry<String, String>> enrolSet = enrolMap.entrySet();
        for (Map.Entry<String, String> map : enrolSet
                ) {
            str.append(map.getKey()).append("=").append(map.getValue()).append("&");
        }
        str.deleteCharAt(str.length() - 1);
        return str;
    }
}
