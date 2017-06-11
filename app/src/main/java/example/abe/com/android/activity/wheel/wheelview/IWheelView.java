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

/**
 * 滚轮抽象接口
 *
 * @author venshine
 */
public interface IWheelView<T> {

    /**
     * 获取滚轮是否循环滚动
     * @return 是否循环滚动
     */
    boolean getLoop();

    /**
     * 设置滚轮是否循环滚动
     *
     * @param loop 循环滚动
     */
    void setLoop(boolean loop);

    /**
     * 获取滚轮个数
     * @return 滚轮个数
     */
    int getWheelSize();

    /**
     * 设置滚轮个数
     *
     * @param wheelSize 滚轮个数
     */
    void setWheelSize(int wheelSize);

    /**
     * 获取滚轮数据适配器
     * @return 滚轮数据适配器
     */
    AbsWheelAdapter<T> getWheelAdapter();

    /**
     * 设置滚轮数据源适配器
     *
     * @param adapter 滚轮数据适配器
     */
    void setWheelAdapter(AbsWheelAdapter<T> adapter);

    /**
     * 获取当前滚轮位置
     *
     * @return 滚轮当前位置
     */
    int getCurrentPosition();

    /**
     * 设置滚轮位置
     *
     * @param position 滚轮位置
     */
    void setCurrentPosition(int position);

    /**
     * 获取滚轮滑动停止时事件，滚轮选中可见项监听
     * @return
     */
    OnWheelItemSelectedListener<T> getOnWheelItemSelectedListener();

    /**
     * 设置滚轮滑动停止时事件，滚轮选中可见项监听
     *
     * @param listener 滚轮选中可见项监听
     */
    void setOnWheelItemSelectedListener(OnWheelItemSelectedListener<T> listener);

    /**
     * 滚轮选中可见项监听
     */
    interface OnWheelItemSelectedListener<T> {
        void onItemSelected(int position, T t);
    }
}
