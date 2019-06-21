package neo.vn.lottery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.lottery.R;
import neo.vn.lottery.model.Tab;

public class TabLotteryViewHolder extends RecyclerView.ViewHolder {
    public TabLotteryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.container)
    RelativeLayout container;

    public void binData(Tab tabLottery) {
        tvTitle.setText(tabLottery.getName());
        container.setSelected(tabLottery.getChecked());
    }

}
