package de.rheinfabrik.heimdalldroid.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.rheinfabrik.heimdalldroid.R;
import de.rheinfabrik.heimdalldroid.network.models.TraktTvList;

/**
 * View holder used to display a liked list.
 */
public class TraktTvListViewHolder extends RecyclerView.ViewHolder {

    // Members

    @Bind(R.id.titleTextView)
    protected TextView mTitleTextView;

    @Bind(R.id.descriptionTextView)
    protected TextView mDescriptionTextView;

    @Bind(R.id.likeCountTextView)
    protected TextView mLikeCountTextView;

    // Constructor

    public TraktTvListViewHolder(View itemView) {
        super(itemView);

        // Inject views
        ButterKnife.bind(this, itemView);
    }

    // Public Api

    public void bind(TraktTvList traktTvList) {

        // Set title
        mTitleTextView.setText(traktTvList.name);

        // Set description
        mDescriptionTextView.setText(traktTvList.description);

        // Set like count
        mLikeCountTextView.setText(String.valueOf(traktTvList.numberOfLikes) + itemView.getContext().getString(R.string.likes_postfix));
    }
}
