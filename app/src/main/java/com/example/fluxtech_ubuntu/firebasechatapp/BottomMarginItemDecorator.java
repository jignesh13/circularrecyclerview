package com.example.fluxtech_ubuntu.firebasechatapp;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BottomMarginItemDecorator extends RecyclerView.ItemDecoration {
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
            outRect.bottom = parent.getContext().getResources().getDimensionPixelSize(R.dimen.recycler_margin_bottom);
        }
    }
}
