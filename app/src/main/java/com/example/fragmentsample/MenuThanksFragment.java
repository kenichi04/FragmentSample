package com.example.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MenuThanksFragment extends Fragment {
    /* このフラグメントが所属するアクティビティオブジェクト */
    private Activity _parentActivity;
    /* 大画面かどうかの判定フラグ */
    private boolean _isLayoutXLarge = true;

    public MenuThanksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 所属するアクティビティオブジェクト取得
        _parentActivity = getActivity();

        // 同一画面にリストフラグメントが存在するかどうか
        FragmentManager manager = getFragmentManager();
        MenuListFragment menuListFragment
                = (MenuListFragment) manager.findFragmentById(R.id.fragmentMenuList);
        if (menuListFragment == null) {
            _isLayoutXLarge = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // フラグメントで表示する画面をインフレート
        View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);

        Bundle extras;

        if (_isLayoutXLarge) {
            // このフラグメントに埋め込まれた引継ぎデータを取得
            extras = getArguments();

        } else {   // 通常画面の場合
            // 所属アクティビティからインテント取得
            Intent intent = _parentActivity.getIntent();
            // インテントから引継ぎデータをまとめたBundleオブジェクトを取得
            extras = intent.getExtras();
        }

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
            if (_isLayoutXLarge) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                // 自分自身を削除
                transaction.remove(MenuThanksFragment.this);
                transaction.commit();

            } else {
                // 通常画面の場合は自分が所属するアクティビティを終了
                _parentActivity.finish();
            }
        }
    }
}