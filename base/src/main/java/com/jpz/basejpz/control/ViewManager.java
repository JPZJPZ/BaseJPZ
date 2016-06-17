package com.jpz.basejpz.control;

import android.app.Activity;
import android.view.View;

import com.jpz.basejpz.view.BaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangjp on 2016/6/14.
 */
public class ViewManager {
    private static ViewManager manager;
    private static final String LOCK = "lock";
    Map<String, List<View>> map = new HashMap<String, List<View>>();
    Map<String, List<View>> group = new HashMap<String, List<View>>();

    public static ViewManager getInstance() {
        if (manager == null) {
            synchronized (LOCK) {
                if (manager == null) {
                    manager = new ViewManager();
                }
            }
        }

        return manager;
    }

    private ViewManager() {
    }

    public void put(Activity activity, View view) {
        List<View> list = getList(activity);
        if (list == null) {
            list = new ArrayList<View>();
            list.add(view);
            map.put(activity.getComponentName().flattenToString(), list);
        } else {
            list.add(view);
        }

    }

    public List<View> getList(Activity activity) {
        return map.get(activity.getComponentName().flattenToString());
    }

    public void remove(Activity activity, View view) {
        List<View> list = getList(activity);
        if (list == null) {
            return;
        }
        list.remove(view);
    }

    public List<View> getList(String groupName) {
        return map.get(groupName);
    }



    public void putViewToGroup(String groupName, View view) {
        List<View> list = getList(groupName);
        if (list == null) {
            list = new ArrayList<View>();
            list.add(view);
            map.put(groupName, list);
        } else {
            list.add(view);
        }
    }

    public List<View> getViewsInGroup(String groupName) {
        return getList(groupName);
    }

    public void refreshGroupView(String groupName) {
        List<View> list = getList(groupName);
        if (list != null && list.size() != 0) {
            for (View v : list) {
                v.invalidate();
            }
        }
    }

    public void moveFollow(View view, String groupName, float deltaX, float deltaY) {
        List<View> list = getList(groupName);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                BaseView baseView = (BaseView) list.get(i);
                if (baseView == view) {
                    continue;
                }
                baseView.setTranslationX(baseView.getXPosition() + deltaX);
                baseView.setTranslationY(baseView.getYPosition() + deltaY);
                if (!baseView.isMoving()) {
                    baseView.setXPosition(baseView.getX());
                    baseView.setYPosition(baseView.getY());
                }
            }
        }
    }

    public void setIsMoving(String groupName, boolean isMoving) {
        List<View> list = getList(groupName);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                BaseView baseView = (BaseView) list.get(i);
                baseView.setIsMoving(isMoving);
                if (!isMoving) {
                    baseView.setXPosition(baseView.getX());
                    baseView.setYPosition(baseView.getY());
                }
            }
        }
    }

}
