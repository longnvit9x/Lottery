package neo.vn.lottery.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.lottery.R;
import neo.vn.lottery.model.SportItem;
import neo.vn.lottery.model.Tab;
import neo.vn.lottery.presenter.SportPresenter;
import neo.vn.lottery.ui.adapter.SportAdapter;
import neo.vn.lottery.ui.adapter.TabAdapter;
import neo.vn.lottery.view.SportView;

public class SportFragment extends BaseFragment implements SportAdapter.Interactor, TabAdapter.Interactor, SportView {

    @BindView(R.id.rcv_sport)
    RecyclerView rcvSport;
    @BindView(R.id.sp_tour)
    Spinner spTour;
    @BindView(R.id.rcv_tab)
    RecyclerView rcvTab;
    private TabAdapter adapterTab;
    private ArrayList<Tab> listTab;

    private ArrayList<SportItem> list;
    private SportAdapter adapter;
    private ArrayList<String> listTour;
    private SportPresenter presenter;

    public static SportFragment newInstance() {
        SportFragment fragment = new SportFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SportPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        listTab = new ArrayList<>();
        listTab.add(new Tab(getResources().getString(R.string.NHA), true));
        listTab.add(new Tab(getResources().getString(R.string.TBN), false));
        listTab.add(new Tab(getResources().getString(R.string.BDA), false));
        listTab.add(new Tab(getResources().getString(R.string.CP), false));
        listTab.add(new Tab(getResources().getString(R.string.EL), false));
        listTab.add(new Tab(getResources().getString(R.string.VN), false));
        adapterTab = new TabAdapter(getContext(), listTab, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false);
        rcvTab.setLayoutManager(layoutManager);
        rcvTab.setAdapter(adapterTab);
        listTour = new ArrayList<>();
        listTour.add("Giải hạng nhất");
        listTour.add("Giải hạng hai");
        listTour.add("Giải hạng cúp nhà vua");
        listTour.add("Giải u23");
        ArrayAdapter<String> adapterTour = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listTour);
        adapterTour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTour.setAdapter(adapterTour);

        list = new ArrayList<>();
        list.add(new SportItem(true));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(true));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(true));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(true));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(true));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        list.add(new SportItem(false));
        adapter = new SportAdapter(getContext(), list, this);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rcvSport.setLayoutManager(layoutManager1);
        rcvSport.setAdapter(adapter);
    }

    @Override
    public void onSelect(Integer pos) {

    }

    @Override
    public void onSelectMatch(Integer pos) {

    }
}
