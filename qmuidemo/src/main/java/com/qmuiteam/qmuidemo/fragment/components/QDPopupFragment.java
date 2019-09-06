/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qmuiteam.qmuidemo.fragment.components;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.layout.QMUIFrameLayout;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.popup.QMUIFullScreenPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.qmuiteam.qmuidemo.R;
import com.qmuiteam.qmuidemo.base.BaseFragment;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.qmuiteam.qmuidemo.manager.QDDataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author cginechen
 * @date 2017-03-27
 */

@Widget(widgetClass = QMUIPopups.class, iconRes = R.mipmap.icon_grid_popup)
@LatestVisitRecord
public class QDPopupFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;

    private QMUIPopup mNormalPopup;

    @OnClick(R.id.actionBtn1)
    void onClickBtn1(View v) {
        TextView textView = new TextView(getContext());
        textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext(), 4), 1.0f);
        int padding = QMUIDisplayHelper.dp2px(getContext(), 20);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText("QMUIBasePopup 可以设置其位置以及显示和隐藏的动画");
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.app_color_description));
        mNormalPopup = QMUIPopups.popup(getContext(), QMUIDisplayHelper.dp2px(getContext(), 250))
                .preferredDirection(QMUIPopup.DIRECTION_BOTTOM)
                .view(textView)
                .edgeProtection(QMUIDisplayHelper.dp2px(getContext(), 20))
                .offsetX(QMUIDisplayHelper.dp2px(getContext(), 20))
                .offsetYIfBottom(QMUIDisplayHelper.dp2px(getContext(), 5))
                .shadow(true)
                .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Toast.makeText(getContext(), "onDismiss", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(v);
    }

    @OnClick(R.id.actionBtn2)
    void onClickBtn2(View v) {
        String[] listItems = new String[]{
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
        };
        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);

        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item, data);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Item " + (i + 1), Toast.LENGTH_SHORT).show();
                if (mNormalPopup != null) {
                    mNormalPopup.dismiss();
                }
            }
        };
        mNormalPopup = QMUIPopups.listPopup(getContext(),
                QMUIDisplayHelper.dp2px(getContext(), 250),
                QMUIDisplayHelper.dp2px(getContext(), 300),
                adapter,
                onItemClickListener)
                .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                .preferredDirection(QMUIPopup.DIRECTION_TOP)
                .shadow(true)
                .offsetYIfTop(QMUIDisplayHelper.dp2px(getContext(), 5))
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Toast.makeText(getContext(), "onDismiss", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(v);
    }

    @OnClick(R.id.actionBtn3)
    void onClickBtn3(View v) {
        TextView textView = new TextView(getContext());
        textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext(), 4), 1.0f);
        int padding = QMUIDisplayHelper.dp2px(getContext(), 20);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText("通过 dimAmount() 设置背景遮罩");
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.app_color_description));
        mNormalPopup = QMUIPopups.popup(getContext(), QMUIDisplayHelper.dp2px(getContext(), 250))
                .preferredDirection(QMUIPopup.DIRECTION_BOTTOM)
                .view(textView)
                .edgeProtection(QMUIDisplayHelper.dp2px(getContext(), 20))
                .dimAmount(0.6f)
                .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Toast.makeText(getContext(), "onDismiss", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(v);
    }

    @OnClick(R.id.actionBtn4)
    void onClickBtn4(View v) {
        final TextView textView = new TextView(getContext());
        textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext(), 4), 1.0f);
        int padding = QMUIDisplayHelper.dp2px(getContext(), 20);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText("加载中...");
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.app_color_description));
        mNormalPopup = QMUIPopups.popup(getContext(), QMUIDisplayHelper.dp2px(getContext(), 250))
                .preferredDirection(QMUIPopup.DIRECTION_BOTTOM)
                .view(textView)
                .edgeProtection(QMUIDisplayHelper.dp2px(getContext(), 20))
                .dimAmount(0.6f)
                .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Toast.makeText(getContext(), "onDismiss", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(v);

        // 这里只是演示，实际情况应该考虑数据加载完成而 Popup 被 dismiss 的情况
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("使用 Popup 最好是一开始就确定内容宽高，" +
                        "如果宽高位置会变化，系统会有一个的移动动画不受控制，体验并不好");
            }
        }, 2000);
    }

    @OnClick(R.id.actionBtn5)
    void onClickBtn5(View v) {
        QMUIFrameLayout frameLayout = new QMUIFrameLayout(getContext());
        frameLayout.setBackgroundColor(Color.WHITE);
        frameLayout.setRadius(QMUIDisplayHelper.dp2px(getContext(), 12));
        int padding = QMUIDisplayHelper.dp2px(getContext(), 20);
        frameLayout.setPadding(padding, padding, padding, padding);

        TextView textView = new TextView(getContext());
        textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext(), 4), 1.0f);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText("这是自定义显示的内容");
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.app_color_description));
        textView.setGravity(Gravity.CENTER);


        int size = QMUIDisplayHelper.dp2px(getContext(), 200);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(size, size);
        frameLayout.addView(textView, lp);

        QMUIPopups.fullScreenPopup(getContext())
                .view(frameLayout)
                .closeBtn(true)
                .onBlankClick(new QMUIFullScreenPopup.OnBlankClickListener() {
                    @Override
                    public void onBlankClick() {
                        Toast.makeText(getContext(), "点击到空白区域", Toast.LENGTH_SHORT).show();
                    }
                })
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Toast.makeText(getContext(), "onDismiss", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(v);
    }

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_popup, null);
        ButterKnife.bind(this, root);
        initTopBar();
        return root;
    }


    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopBar.setTitle(QDDataManager.getInstance().getName(this.getClass()));
    }
}
