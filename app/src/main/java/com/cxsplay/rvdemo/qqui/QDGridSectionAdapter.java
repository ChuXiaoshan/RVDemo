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


package com.cxsplay.rvdemo.qqui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.cxsplay.rvdemo.R;
import com.cxsplay.rvdemo.databinding.ItemRewardBinding;
import com.cxsplay.rvdemo.databinding.ItemRewardSectionBinding;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;

public class QDGridSectionAdapter extends QMUIDefaultStickySectionAdapter<SectionHeader, SectionItem> {

    @NonNull
    @Override
    protected QMUIStickySectionAdapter.ViewHolder onCreateSectionHeaderViewHolder(@NonNull ViewGroup viewGroup) {
        ItemRewardSectionBinding bind = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_reward_section, viewGroup, false);
        return new ViewHolder(bind.getRoot());
    }

    @NonNull
    @Override
    protected QMUIStickySectionAdapter.ViewHolder onCreateSectionItemViewHolder(@NonNull ViewGroup viewGroup) {
        ItemRewardBinding bind = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_reward, viewGroup, false);
        return new ViewHolder(bind.getRoot());
    }

//    @NonNull
//    @Override
//    protected QMUIStickySectionAdapter.ViewHolder onCreateSectionLoadingViewHolder(@NonNull ViewGroup viewGroup) {
//        return new ViewHolder(new QDLoadingItemView(viewGroup.getContext()));
//    }

    @Override
    protected void onBindSectionHeader(final ViewHolder holder, final int position, QMUISection<SectionHeader, SectionItem> section) {


//        QDSectionHeaderView itemView = (QDSectionHeaderView) holder.itemView;
//        itemView.render(section.getHeader(), section.isFold());
//        itemView.getArrowView().setOnClickListener(v -> {
//            int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
//            toggleFold(pos, false);
//        });
    }

    @Override
    protected void onBindSectionItem(ViewHolder holder, int position, QMUISection<SectionHeader, SectionItem> section, int itemIndex) {
//        ((TextView) holder.itemView).setText(section.getItemAt(itemIndex).getText());
    }
}
