package neo.vn.lottery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.lottery.R;

public class SportSectionViewHolder extends RecyclerView.ViewHolder {
    public SportSectionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @BindView(R.id.tv_date)
    TextView tvDate;

    public void bindData() {

    }
}
