package neo.vn.lottery.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import neo.vn.lottery.R;
import neo.vn.lottery.model.Tab;

public class TabAdapter extends RecyclerView.Adapter<TabLotteryViewHolder> {
    private Context context;
    private ArrayList<Tab> list;
    private Interactor interactor;

    public TabAdapter(Context context, ArrayList<Tab> list, Interactor interactor) {
        this.list = list;
        this.context = context;
        this.interactor = interactor;
    }

    @Override
    public TabLotteryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_lottery, viewGroup, false);
        return new TabLotteryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TabLotteryViewHolder tabLotteryViewHolder, int positon) {
        tabLotteryViewHolder.binData(list.get(positon));
        tabLotteryViewHolder.itemView.setOnClickListener(v -> {
            if (tabSelectedOld != positon) {
                list.get(tabSelectedOld).setChecked(false);
                list.get(positon).setChecked(true);
                notifyItemChanged(tabSelectedOld);
                notifyItemChanged(positon);
                tabSelectedOld = positon;
            }
        });
    }

    private Integer tabSelectedOld = 0;

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface Interactor {
        void onSelect(Integer pos);
    }
}
