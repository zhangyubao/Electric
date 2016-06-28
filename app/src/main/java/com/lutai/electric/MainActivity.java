package com.lutai.electric;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lutai.electric.base.BaseActivity;
import com.lutai.electric.fragment.CommonFragment;
import com.lutai.electric.fragment.HomeFragment;
import com.lutai.electric.fragment.MineFragment;
import com.lutai.electric.fragment.SystemFragment;
import com.lutai.electric.utils.SystemBarTintManager;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import me.majiajie.pagerbottomtabstrip.Controller;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.menu_tab)
    RadioGroup menuTab;
    @Bind(R.id.rg_common)
    RadioGroup menuCommon;
    @Bind(R.id.rbtn_common)
    RadioButton mRbtCommon;

    @Bind(R.id.iv_common)
    ImageView commonView;
    @Bind(R.id.tv_common)
    TextView commonText;
    @Bind(R.id.ll_main_container)
    RelativeLayout mainContainer;

    private long mExitTime;
    //底部导航栏的控制
    private Controller mController;
    private FragmentManager mManager;
    //当前正在显示的Fragment界面
    private Fragment currentVisiable;


    private static final int MENU_HOME = 0x00;
    private static final int MENU_SYSTEM = 0x01;
    private static final int MENU_COMMON = 0x02;
    private static final int MENU_MINNE = 0x03;
    private HashMap<String, Fragment> mFragments = new HashMap<String, Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //4.4状态栏设置透明色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // 创建状态栏的管理实例
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 激活导航栏设置
            tintManager.setNavigationBarTintEnabled(true);
            // 设置一个颜色给系统栏
            tintManager.setTintColor(Color.parseColor("#343c52"));
        }
        //5.0状态栏设
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        mManager = getSupportFragmentManager();
        menuTab.setOnCheckedChangeListener(this);
        ((RadioButton) (menuTab.getChildAt(0))).setChecked(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.ll_common)
    public void commonSelect() {
        for (int i = 0; i < menuTab.getChildCount(); i++) {
            ((RadioButton) menuTab.getChildAt(i)).setChecked(false);
            ((RadioButton) menuTab.getChildAt(i)).setFocusable(false);
        }
        commonText.setTextColor(getResources().getColor(R.color.menu_text_press));
        commonView.setBackgroundResource(R.mipmap.icon_star_normal);
        mainContainer.setBackgroundColor(getResources().getColor(R.color.common_main_bg));
        generateFragment("common", CommonFragment.class);
    }

    /**
     * 创建并添加Fragment
     *
     * @param key
     * @param clazz
     */
    private void generateFragment(String key, Class clazz) {
        //通过show()与hide()方法来做界面切换
        try {
            FragmentTransaction transaction = mManager.beginTransaction();
            Fragment fragment = mFragments.get(key);
            if (fragment == null) {
                Logger.d("generate fragment~~~~~~~~~~~~~~~~");
                fragment = (Fragment) clazz.newInstance();
                mFragments.put(key, fragment);
                transaction.add(R.id.ll_container, fragment);
            }
            if (currentVisiable != null) {
                transaction.hide(currentVisiable).show(fragment);
            } else {
                transaction.show(fragment);
            }
            transaction.commit();
            currentVisiable = fragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        commonText.setTextColor(getResources().getColor(R.color.menu_text_normal));
        commonView.setBackgroundResource(R.mipmap.icon_star_selected);
        switch (checkedId) {
            case R.id.rbtn_home:
                mainContainer.setBackgroundColor(getResources().getColor(R.color.white));
                group.getChildAt(0).setFocusable(true);
                generateFragment("home", HomeFragment.class);
                break;
            case R.id.rbtn_system:
                mainContainer.setBackgroundColor(getResources().getColor(R.color.common_main_bg));
                group.getChildAt(1).setFocusable(true);
                generateFragment("system", SystemFragment.class);
                break;
            case R.id.rbtn_mine:
                mainContainer.setBackgroundColor(getResources().getColor(R.color.common_main_bg));
                group.getChildAt(2).setFocusable(true);
                generateFragment("mine", MineFragment.class);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
