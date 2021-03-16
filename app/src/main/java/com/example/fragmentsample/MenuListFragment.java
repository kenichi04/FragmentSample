package com.example.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MenuListFragment extends Fragment {

    /* このフラグメントが所属するアクティビティオブジェクト */
    private Activity _parentActivity;

    public MenuListFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        ListView lvMenu = view.findViewById(R.id.lvMenu);

        // SinpleAdapterで使用するListオブジェクト
        List<Map<String, String>> menuList = new ArrayList<>();

        //Map生成し、menuListに格納
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", "800円");
        menuList.add(menu);

        //listに追加するhashmapを任意の数だけ追加していく
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", "1000円");
        menuList.add(menu);

        // SimpleAdapterの第4、第5引数用
        String[] from = {"name", "price"};
        int [] to = {android.R.id.text1, android.R.id.text2};

        // SimpleAdapter生成
        SimpleAdapter adapter
                = new SimpleAdapter(_parentActivity, menuList, android.R.layout.simple_list_item_2, from, to);

        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new ListItemClickListener());

        return view;
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //タップされた行のデータ取得。SimpleAdapterでは1行分はMap型。
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);

            //nameキーとpriceキーに入っている値を取得
            String menuName = item.get("name");
            String menuPrice = item.get("price");

            //インテントオブジェクトを作成
            Intent intent = new Intent(_parentActivity, MenuThanksActivity.class);

            //次の画面に送るデータの格納。intentオブジェクトのメソッド。
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice);

            //第二画面の起動
            startActivity(intent);
        }

    }
}