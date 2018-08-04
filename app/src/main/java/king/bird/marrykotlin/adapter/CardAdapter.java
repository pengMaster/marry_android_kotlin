package king.bird.marrykotlin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.view.jameson.library.CardAdapterHelper;

import java.util.ArrayList;
import java.util.List;

import jameson.io.library.util.ToastUtils;
import king.bird.marrykotlin.R;

/**
 * Created by jameson on 8/30/16.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<String> mList = new ArrayList<>();
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();
    private Context mContext;

    public CardAdapter(List<String> mList, Context context) {
        this.mList = mList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_item, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        Glide.with(mContext).load(mList.get(position)).into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(holder.mImageView.getContext(), "" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}
