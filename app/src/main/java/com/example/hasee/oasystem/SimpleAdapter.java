package com.example.hasee.oasystem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * RecyclerView的adapter
 * Created by hasee on 2016/9/12.
 */
public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private LayoutInflater mInflater;
    private Context mContext;
    protected List<ItemBean> mDatas;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public SimpleAdapter(Context context, List<ItemBean> datas){
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    //创建ViewHoder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_single_textview,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    //绑定ViewHolder
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

        ItemBean bean = mDatas.get(position);
        holder.mImageView.setImageResource(bean.ivPic);
        holder.mTextView.setText(bean.title);

        setUpItemEvent(holder);
    }

    protected void setUpItemEvent(final MyViewHolder holder){
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,layoutPosition);

                                    }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

//包含item中的所有控件
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView mTextView;
    ImageView mImageView;

    public MyViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.id_iv);
        mTextView = (TextView) itemView.findViewById(R.id.id_tv);
    }
}