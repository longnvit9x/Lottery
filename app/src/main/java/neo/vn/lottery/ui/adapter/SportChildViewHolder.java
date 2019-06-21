package neo.vn.lottery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.lottery.R;
import neo.vn.lottery.model.TranDau;

public class SportChildViewHolder extends RecyclerView.ViewHolder {
    public SportChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.img_team_1)
    ImageView imgTeam1;
    @BindView(R.id.tv_name_team_1)
    TextView tvNameTeam1;
    @BindView(R.id.tv_number_team_1)
    TextView tvNumberTeam1;
    @BindView(R.id.img_team_2)
    ImageView imgTeam2;
    @BindView(R.id.tv_name_team_2)
    TextView tvNameTeam2;
    @BindView(R.id.tv_number_team_2)
    TextView tvNumberTeam2;
    @BindView(R.id.tv_house)
    TextView tvHouse;

    public void bindData(TranDau tranDau) {
        tvNameTeam1.setText(tranDau.doiNha);
        tvNameTeam2.setText(tranDau.doiKhach);
        tvHouse.setText(tranDau.gioDa);

        if(tranDau.banThangDoiNha.equals("-1") || tranDau.banThangDoiNha.equals("")){
            container.setBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
            tvNumberTeam1.setText("...");
            tvNumberTeam2.setText("...");
        } else {
            container.setBackgroundColor(itemView.getResources().getColor(R.color.white));
            tvNumberTeam1.setText(tranDau.banThangDoiNha);
            tvNumberTeam2.setText(tranDau.banThangDoiKhach);
        }
    }
}
