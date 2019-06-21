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
import neo.vn.lottery.model.Tab;
import neo.vn.lottery.TimeUtils;
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
        presenter= new LotteryPresenter();
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
        presenter.getDataFromServer();
    }

    @Override
    public void onSelect(Integer pos) {

    }

    private void initView() {
        listTab = new ArrayList<>();
        listTab.add(new Tab(getResources().getString(R.string.MB), true));
        listTab.add(new Tab(getResources().getString(R.string.MN), false));
        listTab.add(new Tab(getResources().getString(R.string.MT), false));
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

    }
}
