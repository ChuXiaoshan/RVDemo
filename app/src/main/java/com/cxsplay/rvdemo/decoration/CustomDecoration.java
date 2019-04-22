package com.cxsplay.rvdemo.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomDecoration extends RecyclerView.ItemDecoration {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private final RectF mBoundsF = new RectF();
    private Drawable mDrawable;

    public CustomDecoration(Context context, int resId) {
        mDrawable = context.getResources().getDrawable(resId);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//            int top = child.getBottom() + params.bottomMargin;
//            int bottom = top + mDrawable.getIntrinsicHeight();
//            mDrawable.setBounds(left, top, right, bottom);
//            mDrawable.draw(c);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
            this.mBoundsF.set(child.getLeft(), child.getBottom(), child.getRight(), child.getBottom() + 20);
            c.drawRoundRect(this.mBoundsF, 1, 1, paint);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
    }
}