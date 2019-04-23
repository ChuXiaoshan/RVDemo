package com.cxsplay.rvdemo.decoration;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.jetbrains.annotations.NotNull;

public class CustomDecoration extends RecyclerView.ItemDecoration {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private final RectF mBoundsF = new RectF();
    private Drawable mDrawable;

    public CustomDecoration(Context context, int resId) {
        mDrawable = context.getResources().getDrawable(resId);
    }

//    @Override
//    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        c.save();
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//        int childCount = parent.getChildCount();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.TRANSPARENT);
//        for (int i = 0; i < childCount; i++) {
//            View child = parent.getChildAt(i);
////            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
////            int top = child.getBottom() + params.bottomMargin;
////            int bottom = top + mDrawable.getIntrinsicHeight();
////            mDrawable.setBounds(left, top, right, bottom);
////            mDrawable.draw(c);
//            this.mBoundsF.set(child.getLeft(), child.getBottom(), child.getRight(), child.getBottom() + 20);
//            c.drawRoundRect(this.mBoundsF, 1, 1, paint);
//        }
//        c.restore();
//    }


    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager glm = (GridLayoutManager) layoutManager;
            int spanCount = glm.getSpanCount();
            GridLayoutManager.SpanSizeLookup spanSizeLookup = glm.getSpanSizeLookup();
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (spanCount == spanSizeLookup.getSpanSize(childAdapterPosition)) {
                outRect.set(0, 10, 0, 0);
            }
        }
    }
}