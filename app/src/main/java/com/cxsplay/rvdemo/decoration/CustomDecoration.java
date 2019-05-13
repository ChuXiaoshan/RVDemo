package com.cxsplay.rvdemo.decoration;

import android.graphics.Rect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import org.jetbrains.annotations.NotNull;

public class CustomDecoration extends RecyclerView.ItemDecoration {

    private int offset;

    public CustomDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager glm = (GridLayoutManager) layoutManager;
            int spanCount = glm.getSpanCount();
            GridLayoutManager.SpanSizeLookup spanSizeLookup = glm.getSpanSizeLookup();
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (spanCount == spanSizeLookup.getSpanSize(childAdapterPosition)) {
                outRect.set(0, offset, 0, 0);
            }
        }
    }
}