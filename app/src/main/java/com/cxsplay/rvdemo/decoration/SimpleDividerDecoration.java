package com.cxsplay.rvdemo.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by CxS on 2018/12/14
 */
public class SimpleDividerDecoration extends RecyclerView.ItemDecoration {


    private final Rect mBounds = new Rect();
    private final RectF mBoundsF = new RectF();
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private int dividerHeight;

    public SimpleDividerDecoration(int dividerHeight, @ColorInt int color) {
        paint.setColor(color);
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//        outRect.set(0, 0, 0, dividerHeight);
        int gap = dividerHeight / 2;
        outRect.set(dividerHeight + gap, dividerHeight + gap, dividerHeight + gap, dividerHeight + gap);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        canvas.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(dividerHeight);
            this.mBoundsF.set(child.getLeft() - dividerHeight + dividerHeight / 2 + Math.round(child.getTranslationX()),
                    child.getTop() - dividerHeight + dividerHeight / 2 + Math.round(child.getTranslationY()),
                    child.getRight() + dividerHeight - dividerHeight / 2 + Math.round(child.getTranslationX()),
                    child.getBottom() + dividerHeight - dividerHeight / 2 + Math.round(child.getTranslationY()));
            canvas.drawRoundRect(this.mBoundsF, 1, 1, paint);
        }
        canvas.restore();
    }
}
