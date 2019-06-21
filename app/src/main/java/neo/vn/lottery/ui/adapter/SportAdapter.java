package neo.vn.lottery.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import neo.vn.lottery.R;
import neo.vn.lottery.model.SportItem;

public class SportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<SportItem> list;
    private Interactor interactor;
    private static final int ITEM_SECTION = 0;
    private static final int ITEM_CHILD = 1;

    public SportAdapter(Context context,
                        ArrayList<SportItem> list,
                        Interactor interactor) {
        this.context = context;
        this.list = list;
        this.interactor = interactor;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType== ITEM_SECTION){
            View view= LayoutInflater.from(context).inflate(R.layout.item_sport_section, viewGroup,false);
            return new SportSectionViewHolder(view);
        } else {
            View view= LayoutInflater.from(context).inflate(R.layout.item_sport_child, viewGroup,false);
            return new SportChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getSection()) {
            return ITEM_SECTION;
        } else {
            return ITEM_CHILD;
        }
    }

   public interface Interactor {
        void onSelectMatch(Integer pos);
    }
}
