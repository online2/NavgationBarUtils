package com.linfc.www.navgationbarutils.utils;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.FitWindowsLinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * created by Linfc on 2019/2/12
 * NavgationBar 工具类
 */
public class NavgationbarUtils {

    private final Window mWindow;
    private final View mContentView;
    private ViewGroup mOnlyLinearLayout;
    private int navigationBarHeight = 0;

    private NavgationbarUtils(Activity activity) {
        mWindow = activity.getWindow();
        mContentView = activity.findViewById(android.R.id.content);
        findRootLinearLayout();
    }

    private NavgationbarUtils(Fragment fragment) {
        mWindow = fragment.getActivity().getWindow();
        mContentView = fragment.getView();
        findRootLinearLayout();
    }


    private NavgationbarUtils(Dialog dialog) {
        mWindow = dialog.getWindow();
        mContentView = dialog.findViewById(android.R.id.content);
        findRootLinearLayout();
    }

    /**
     * 从R.id.content从上遍历，拿到 DecorView 下的唯一子布局LinearLayout
     * 获取对应的bottomMargin 即可得到对应导航栏的高度，0为关闭了或没有导航栏
     */
    private void findRootLinearLayout() {
        try {
            ViewGroup decorView = (ViewGroup) mWindow.getDecorView();
            if (mContentView != null) {
                View tempView = mContentView;
                while (tempView.getParent() != decorView) {
                    ViewGroup parent = (ViewGroup) tempView.getParent();
                    if (parent instanceof LinearLayout) {
                        //如果style设置了不带toolbar,mContentView上层布局会由原来的 ActionBarOverlayLayout->FitWindowsLinearLayout)
                        if (parent instanceof FitWindowsLinearLayout) {
                            tempView = parent;
                            continue;
                        } else {
                            mOnlyLinearLayout = parent;
                            break;
                        }
                    } else {
                        tempView = parent;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return false 关闭了NavgationBar ,true 开启了
     */
    public boolean navgationbarIsOpen() {
        if (mOnlyLinearLayout != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mOnlyLinearLayout.getLayoutParams();
            navigationBarHeight = layoutParams.bottomMargin;
        }
        if (navigationBarHeight == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getNavigationBarHeight() {
        if (mOnlyLinearLayout != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mOnlyLinearLayout.getLayoutParams();
            navigationBarHeight = layoutParams.bottomMargin;
        }
        return navigationBarHeight;
    }
}
