package pk.com.aptech.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HorizontalDividerItemDecoration extends RecyclerView.ItemDecoration
{
    private Drawable divider;

    public HorizontalDividerItemDecoration(Drawable divider) {
        this.divider=divider.mutate();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();

        int childCount=parent.getChildCount();

        for (int i=0; i<childCount-1; i++) {
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params=
                    (RecyclerView.LayoutParams)child.getLayoutParams();

            int top=child.getBottom()+params.bottomMargin;
            int bottom=top+divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}
