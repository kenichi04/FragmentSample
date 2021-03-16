package com.example.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MenuThanksFragment extends Fragment {
    /* このフラグメントが所属するアクティビティオブジェクト */
    private Activity _parentActivity;

    public MenuThanksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 所属するアクティビティオブジェクト取得
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // フラグメントで表示する画面をインフレート
        View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);

        // 所属アクティビティからインテント取得
        Intent intent = _parentActivity.getIntent();
        // インテントから引継ぎデータをまとめたBundleオブジェクトを取得
        Bundle extras = intent.getExtras();

        // 注文した定食と金額用の変数
        String menuName = "";
        String menuPrice = "";

        if(extras != null) {
            menuName = extras.getString("menuName");
            menuPrice = extras.getString("menuPrice");
        }

        TextView tvMenuName = view.findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        Button btBackButton = view.findViewById(R.id.btBackButton);
        btBackButton.setOnClickListener(new ButtonClickListener());

        return view;
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            _parentActivity.finish();
        }
    }
}