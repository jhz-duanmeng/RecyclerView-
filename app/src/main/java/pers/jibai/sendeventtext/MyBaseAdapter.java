package pers.jibai.sendeventtext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {

    private static final String TAG = "MyBaseAdapter";
    private List<T> mList;
    private int mResId;

    public MyBaseAdapter(List<T> mList, int mResId) {
        this.mList = mList;
        this.mResId = mResId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: ");
        return MyViewHolder.getHolder(mResId, parent, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: ");
        bindView(holder, position);
    }

    public abstract void bindView(MyViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "MyViewHolder";
        private SparseArray<View> views = new SparseArray<>();

        public static MyViewHolder getHolder(int mResId, ViewGroup parent, int viewType) {

            MyViewHolder holder;
            View view = LayoutInflater.from(parent.getContext()).inflate(mResId, parent, false);
            holder = new MyViewHolder(view);

            return holder;
        }

        private MyViewHolder(View itemView) {
            super(itemView);
        }


        private <T extends View> T getView(int id) {
            T t = (T) views.get(id);
            if (t == null) {
                t = this.itemView.findViewById(id);
                views.put(id, t);
            }
            return t;
        }

        public MyViewHolder setText(int id, String text) {
            TextView textView = getView(id);
            textView.setText(text);
            return this;
        }

        public MyViewHolder setChecked(int id, boolean isChecked) {
            CheckBox checkBox = getView(id);
            checkBox.setChecked(isChecked);
            return this;
        }

        public MyViewHolder setPicture(int id,int resId){
            ImageView imageView = getView(id);
            imageView.setImageResource(resId);
            return this;
        }



    }
}
