package tkom.kkomar.przypominajka.android;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable separator;

    public DividerItemDecoration(Context context) {
        int[] attrs = new int[]{ android.R.attr.listDivider };
        final TypedArray typedArray = context.obtainStyledAttributes(attrs);
        separator = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDrawOver(Canvas context, RecyclerView parent, RecyclerView.State state) {
        drawVertical(context, parent);
    }

    public void drawVertical(Canvas context, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + separator.getIntrinsicHeight();
            separator.setBounds(left, top, right, bottom);
            separator.draw(context);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, separator.getIntrinsicHeight());
    }
}