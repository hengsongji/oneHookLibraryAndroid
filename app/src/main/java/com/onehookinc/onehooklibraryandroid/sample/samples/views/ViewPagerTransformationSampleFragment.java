package com.onehookinc.onehooklibraryandroid.sample.samples.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onehook.view.pageindicator.PagerIndicator;
import com.onehook.view.pager.SimpleViewPagerAdapter;
import com.onehook.view.pager.SlideShowViewPager;
import com.onehook.view.pager.transformer.CarouselTransformer;
import com.onehook.view.pager.transformer.DepthPageTransformer;
import com.onehook.view.pager.transformer.PagerAlphaTransformer;
import com.onehook.view.pager.transformer.StackTransformer;
import com.onehook.view.pager.transformer.VerticalPagerDefaultTransformer;
import com.onehook.view.pager.transformer.ZoomOutTransformer;
import com.onehook.widget.adapter.InfinitePagerAdapter;
import com.onehookinc.onehooklibraryandroid.R;
import com.onehookinc.onehooklibraryandroid.sample.common.BaseFragment;

public class ViewPagerTransformationSampleFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample_view_pagers_trans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int padding = getResources().getDimensionPixelSize(R.dimen.common_margin_medium);

        final ViewPager p1 = view.findViewById(R.id.fragment_sample_view_pagers_p1);
        p1.setPageTransformer(false, new CarouselTransformer(padding, 1));
        p1.setClipChildren(false);
        p1.setClipToPadding(false);
        p1.setPadding(padding, 0, padding, 0);
        p1.setAdapter(new DemoPager(getContext()));

        final ViewPager p1i = view.findViewById(R.id.fragment_sample_view_pagers_p1_infinite);
        p1i.setPageTransformer(false, new CarouselTransformer(padding * 2, 0.9f));
        p1i.setPadding(padding * 2, 0, padding * 2, 0);
        p1i.setClipChildren(false);
        p1i.setClipToPadding(false);
        final InfinitePagerAdapter infiniteAdapter = new InfinitePagerAdapter<>(new DemoPager(getContext()));
        p1i.setAdapter(infiniteAdapter);
        p1i.setCurrentItem(infiniteAdapter.getMidPosition(0));
        final PagerIndicator pagerIndicator = view.findViewById(R.id.fragment_sample_view_pagers_p1_indicator);
        pagerIndicator.setViewPager(p1i);

        final ViewPager p2 = view.findViewById(R.id.fragment_sample_view_pagers_p2);
        p2.setPageTransformer(false, new DepthPageTransformer(true));
        p2.setAdapter(new DemoPager(getContext()));

        final ViewPager p3 = view.findViewById(R.id.fragment_sample_view_pagers_p3);
        p3.setPageTransformer(false, new PagerAlphaTransformer());
        p3.setAdapter(new DemoPager(getContext()));

        final ViewPager p4 = view.findViewById(R.id.fragment_sample_view_pagers_p4);
        p4.setPageTransformer(true, new StackTransformer());
        p4.setAdapter(new DemoPager(getContext()));

        final ViewPager p5 = view.findViewById(R.id.fragment_sample_view_pagers_p5);
        p5.setPageTransformer(true, new ZoomOutTransformer());
        p5.setAdapter(new DemoPager(getContext()));
    }

    @Override
    public void onToolbarReady(Toolbar toolbar) {
        super.onToolbarReady(toolbar);
        getBaseAcivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getBaseAcivity().getSupportActionBar().setTitle("Pager Transformation Sample");
    }


    private class DemoPager extends SimpleViewPagerAdapter {

        private int mColors[];

        public DemoPager(final Context context) {
            mColors = new int[]{ContextCompat.getColor(context, R.color.red_900),
                    ContextCompat.getColor(context, R.color.gray_800)};
        }

        @Override
        public View createView(LayoutInflater inflater, ViewGroup viewGroup, int position) {
            final TextView tv = new TextView(viewGroup.getContext());
            tv.setTextSize(30);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.WHITE);
            return tv;
        }

        @Override
        public void bindView(View view, int position) {
            final TextView tv = (TextView) view;
            tv.setBackgroundColor(mColors[position % mColors.length]);
            tv.setText(String.valueOf(position));
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
