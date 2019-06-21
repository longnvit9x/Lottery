package neo.vn.lottery.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.lottery.R;
import neo.vn.lottery.TimeUtils;
import neo.vn.lottery.model.Lottery;
import neo.vn.lottery.model.Tab;
import neo.vn.lottery.model.Triple;
import neo.vn.lottery.presenter.LotteryPresenter;
import neo.vn.lottery.ui.adapter.TabAdapter;
import neo.vn.lottery.view.LotteryView;

public class LotteryFragment extends BaseFragment implements TabAdapter.Interactor, LotteryView {

    @BindView(R.id.rcv_tab)
    RecyclerView rcvTab;
    @BindView(R.id.img_previous)
    ImageView imgPrevious;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.img_next)
    ImageView imgNext;
    @BindView(R.id.lnl_previous)
    LinearLayout lnlPrevious;
    @BindView(R.id.lnl_next)
    LinearLayout lnlNext;
    @BindView(R.id.tv_special)
    TextView tvSpecical;
    @BindView(R.id.tv_top1)
    TextView tvTop1;
    @BindView(R.id.tv_top2_1)
    TextView tvTop2_1;
    @BindView(R.id.tv_top2_2)
    TextView tvTop2_2;
    @BindView(R.id.tv_top3_1)
    TextView tvTop3_1;
    @BindView(R.id.tv_top3_2)
    TextView tvTop3_2;
    @BindView(R.id.tv_top3_3)
    TextView tvTop3_3;
    @BindView(R.id.tv_top3_4)
    TextView tvTop3_4;
    @BindView(R.id.tv_top3_5)
    TextView tvTop3_5;
    @BindView(R.id.tv_top3_6)
    TextView tvTop3_6;
    @BindView(R.id.tv_top4_1)
    TextView tvTop4_1;
    @BindView(R.id.tv_top4_2)
    TextView tvTop4_2;
    @BindView(R.id.tv_top4_3)
    TextView tvTop4_3;
    @BindView(R.id.tv_top4_4)
    TextView tvTop4_4;
    @BindView(R.id.tv_top5_1)
    TextView tvTop5_1;
    @BindView(R.id.tv_top5_2)
    TextView tvTop5_2;
    @BindView(R.id.tv_top5_3)
    TextView tvTop5_3;
    @BindView(R.id.tv_top5_4)
    TextView tvTop5_4;
    @BindView(R.id.tv_top5_5)
    TextView tvTop5_5;
    @BindView(R.id.tv_top5_6)
    TextView tvTop5_6;
    @BindView(R.id.tv_top6_1)
    TextView tvTop6_1;
    @BindView(R.id.tv_top6_2)
    TextView tvTop6_2;
    @BindView(R.id.tv_top6_3)
    TextView tvTop6_3;
    @BindView(R.id.tv_top7_1)
    TextView tvTop7_1;
    @BindView(R.id.tv_top7_2)
    TextView tvTop7_2;
    @BindView(R.id.tv_top7_3)
    TextView tvTop7_3;
    @BindView(R.id.tv_top7_4)
    TextView tvTop7_4;
    public Tab tabSelect;

    private LotteryPresenter presenter;
    Calendar dateStart = Calendar.getInstance();
    private TabAdapter adapterTab;
    private ArrayList<Tab> listTab;

    public static LotteryFragment newInstance() {
        LotteryFragment fragment = new LotteryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LotteryPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lottery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        presenter.getDataFromServer(tabSelect.getCode(), TimeUtils.dateNow().getTime());
    }

    @Override
    public void onSelect(Integer pos) {
        tabSelect = listTab.get(pos);
        presenter.getDataFromServer(tabSelect.getCode(), dateStart.getTime());
    }

    private void initView() {
        tabSelect = new Tab(getResources().getString(R.string.MB), false, "mien_bac");
        listTab = new ArrayList<>();
        listTab.add(new Tab(getResources().getString(R.string.MB), true, "mien_bac"));
        listTab.add(new Tab(getResources().getString(R.string.MN), false, "mien_nam"));
        listTab.add(new Tab(getResources().getString(R.string.MT), false, "mien_trung"));
        adapterTab = new TabAdapter(getContext(), listTab, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false);
        rcvTab.setLayoutManager(layoutManager);
        rcvTab.setAdapter(adapterTab);
        Triple<Integer, Integer, Integer> dateInt = TimeUtils.getYearMonthDay(dateStart.getTime());
        String textDate = String.format(getResources().getString(R.string.FORMAT_DATE_TIME_HEADER), "MB", dateInt.third.toString(), dateInt.second.toString(), dateInt.first.toString());
        tvDate.setText(textDate);
        lnlPrevious.setOnClickListener(v -> tvDate.startAnimation(previousAnimation(tvDate)));

        lnlNext.setOnClickListener(v -> tvDate.startAnimation(nextAnimation(tvDate)));
        tvDate.setOnClickListener(v -> {
            showDatePicker();
        });
    }

    private void showDatePicker() {
        DateDialog datePicker = DateDialog.newInstance(dateStart.getTimeInMillis());
        datePicker.setOnDateClickListener((datePicker1, i, i1, i2) -> {
            dateStart.set(Calendar.YEAR, i);
            dateStart.set(Calendar.MONTH, i1);
            dateStart.set(Calendar.DAY_OF_MONTH, i2);
            Triple<Integer, Integer, Integer> dateInt = TimeUtils.getYearMonthDay(dateStart.getTime());
            String textDate = String.format(getResources().getString(R.string.FORMAT_DATE_TIME_HEADER), "MB", dateInt.third.toString(), dateInt.second.toString(), dateInt.first.toString());
            tvDate.setText(textDate);
            onDateChange();
        });
        datePicker.show(getChildFragmentManager(), DateDialog.class.getName());
    }

    private Animation previousAnimation(final TextView tvDate) {
        final Animation inAnim = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_left);
        Animation outAnim = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_right);
        inAnim.setAnimationListener((new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                changeTextDate(tvDate, -1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onDateChange();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }));
        outAnim.setAnimationListener((new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                tvDate.startAnimation(inAnim);
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        }));
        inAnim.setDuration(100L);
        outAnim.setDuration(100L);
        return outAnim;
    }

    private Animation nextAnimation(final TextView tvDate) {
        final Animation inAnim = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_right);
        Animation outAnim = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_left);
        inAnim.setAnimationListener((new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                onDateChange();
            }

            @Override
            public void onAnimationStart(Animation animation) {
                changeTextDate(tvDate, 1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        }));
        outAnim.setAnimationListener((new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                tvDate.startAnimation(inAnim);
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        }));
        inAnim.setDuration(100L);
        outAnim.setDuration(100L);
        return outAnim;
    }

    private void changeTextDate(TextView tvDate, int number) {
        dateStart.add(Calendar.DAY_OF_MONTH, number);
        Triple<Integer, Integer, Integer> dateInt = TimeUtils.getYearMonthDay(dateStart.getTime());
        String textDate = String.format(getResources().getString(R.string.FORMAT_DATE_TIME_HEADER), "MB", dateInt.third.toString(), dateInt.second.toString(), dateInt.first.toString());
        tvDate.setText(textDate);
    }

    private void onDateChange() {
        presenter.getDataFromServer(tabSelect.getCode(), dateStart.getTime());
    }

    @Override
    public void getLotterySuccess(Lottery lottery) {
        tvSpecical.setText(lottery.dacBiet);
        tvTop1.setText(lottery.nhat);
        tvTop2_1.setText(lottery.nhi1);
        tvTop2_2.setText(lottery.nhi2);
        tvTop3_1.setText(lottery.ba1);
        tvTop3_2.setText(lottery.ba2);
        tvTop3_3.setText(lottery.ba3);
        tvTop3_4.setText(lottery.ba4);
        tvTop3_5.setText(lottery.ba5);
        tvTop3_6.setText(lottery.ba6);
        tvTop4_1.setText(lottery.tu1);
        tvTop4_2.setText(lottery.tu2);
        tvTop4_3.setText(lottery.tu3);
        tvTop4_4.setText(lottery.tu4);
        tvTop5_1.setText(lottery.nam1);
        tvTop5_2.setText(lottery.nam2);
        tvTop5_3.setText(lottery.nam3);
        tvTop5_4.setText(lottery.nam4);
        tvTop5_5.setText(lottery.nam5);
        tvTop5_6.setText(lottery.nam6);
        tvTop6_1.setText(lottery.sau1);
        tvTop6_2.setText(lottery.sau2);
        tvTop6_3.setText(lottery.sau3);
        tvTop7_1.setText(lottery.bay1);
        tvTop7_2.setText(lottery.bay2);
        tvTop7_3.setText(lottery.bay3);
        tvTop7_4.setText(lottery.bay4);
    }

    @Override
    public void getLotteryError() {
        tvSpecical.setText("_");
        tvTop1.setText("_");
        tvTop2_1.setText("_");
        tvTop2_2.setText("_");
        tvTop3_1.setText("_");
        tvTop3_2.setText("_");
        tvTop3_3.setText("_");
        tvTop3_4.setText("_");
        tvTop3_5.setText("_");
        tvTop3_6.setText("_");
        tvTop4_1.setText("_");
        tvTop4_2.setText("_");
        tvTop4_3.setText("_");
        tvTop4_4.setText("_");
        tvTop5_1.setText("_");
        tvTop5_2.setText("_");
        tvTop5_3.setText("_");
        tvTop5_4.setText("_");
        tvTop5_5.setText("_");
        tvTop5_6.setText("_");
        tvTop6_1.setText("_");
        tvTop6_2.setText("_");
        tvTop6_3.setText("_");
        tvTop7_1.setText("_");
        tvTop7_2.setText("_");
        tvTop7_3.setText("_");
        tvTop7_4.setText("_");
    }
}
