/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.abe.com.android.activity.wheel.wheelview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 滚轮抽象数据适配器
 *
 * @author venshine
 */
public abstract class AbsWheelAdapter<T> extends BaseAdapter implements IWheelAdapter<T> {

    /**
     * 数据列表
     */
    private List<T> mDataList = null;

    /**
     * 是否循环滚动
     */
    private boolean mLoop = false;

    /**
     * 滚轮个数
     */
    private int mWheelSize = 3;

    @Override
    public abstract View bindView(int position, View convertView, ViewGroup parent);

    @Override
    public abstract void refreshView(int curPosition, int position, View convertView);

    @Override
    public int getDataCount() {
        if (mDataList == null) {
            return 0;
        } else {
            return mDataList.size();
        }
    }

    @Override
    public void setDataList(List<T> list) {
        mDataList = list;
        notifyDataSetChanged();
    }

    @Override
    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public final int getCount() {
        if (mLoop) {
            // 将ListView设置为无限大，从而实现循环滚动效果
            return Integer.MAX_VALUE;
        }
        boolean isEmpty = (mDataList == null || mDataList.size() == 0);
        return !isEmpty ? (mDataList.size() + mWheelSize - 1) : 0;
    }

    @Override
    public final long getItemId(int position) {
        boolean isEmpty = (mDataList == null || mDataList.size() == 0);
        return !isEmpty ? position % mDataList.size() : position;
    }

    @Override
    public final T getItem(int position) {
        boolean isEmpty = (mDataList == null || mDataList.size() == 0);
        return !isEmpty ? mDataList.get(position % mDataList.size()) : null;
    }

    @Override
    public final boolean isEnabled(int position) {
        return false;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        // 获取位置
        if (mDataList == null || mDataList.size() == 0) {
            // 数据为空
            position = -1;
        } else if (mLoop) {
            // 循环滚动
            position = position % mDataList.size();
        } else {
            // 非循环滚动
            if (position < mWheelSize / 2) {
                position = -1;
            } else if (position >= mWheelSize / 2 + mDataList.size()) {
                position = -1;
            } else {
                position = position - mWheelSize / 2;
            }
        }
        // 构造视图
        View view;
        if (position == -1) {
            view = bindView(0, convertView, parent);
        } else {
            view = bindView(position, convertView, parent);
        }
        if (!mLoop) {
            // position == -1，表示为滚轮顶部与尾部的空白项
            if (position == -1) {
                view.setVisibility(View.INVISIBLE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    /**
     * 设置滚轮是否循环滚动
     *
     * @param loop 循环滚动
     */
    final void setLoop(boolean loop) {
        if (loop != mLoop) {
            mLoop = loop;
            notifyDataSetChanged();
        }
    }

    /**
     * 设置滚轮个数
     *
     * @param wheelSize 滚轮个数
     */
    final void setWheelSize(int wheelSize) {
        if (mWheelSize != wheelSize) {
            mWheelSize = wheelSize;
            notifyDataSetChanged();
        }
    }
}
