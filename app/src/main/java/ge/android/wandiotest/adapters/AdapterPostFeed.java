package ge.android.wandiotest.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ge.android.wandiotest.R;
import ge.android.wandiotest.interfaces.OnFragmentInteractiionlistener;
import ge.android.wandiotest.utils.Post;

import java.util.List;

public class AdapterPostFeed extends RecyclerView.Adapter<AdapterPostFeed.ViewHolder> {

    private final List<Post> listOfPosts;
    private final OnFragmentInteractiionlistener mListener;
    private Context mContext;

    public AdapterPostFeed(List<Post> items, OnFragmentInteractiionlistener listener, Context context) {
        listOfPosts = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.post = listOfPosts.get(position);
        holder.mSenderView.setText(holder.post.getName());
        holder.mMessageView.setText(holder.post.getMessage());
        Picasso.with(mContext).load(holder.post.getPhotoUrl()).into(holder.mImgView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onFragmentPostFeedInteraction();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mSenderView;
        final TextView mMessageView;
        final ImageView mImgView;
        Post post;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mSenderView = view.findViewById(R.id.post_title);
            mMessageView = view.findViewById(R.id.post_message);
            mImgView = view.findViewById(R.id.post_image);
        }
    }
}
