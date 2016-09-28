package com.ress.customercoordinatelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ress on 2016/9/28.
 */
public class ContentFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<String> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getActivity());
        initData();
        initEvent();
        return mRecyclerView;
    }

    private void initEvent() {
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(lm);
        MyAdapter adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add("我是第" + i + "条数据");
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.recycler_item, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            myViewHolder.mTextView = (TextView) view.findViewById(R.id.tv);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

}

