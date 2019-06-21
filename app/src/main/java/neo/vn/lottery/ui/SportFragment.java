package neo.vn.lottery.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.vn.lottery.R;
import neo.vn.lottery.model.DateItem;
import neo.vn.lottery.model.GiaiDau;
import neo.vn.lottery.model.SportItem;
import neo.vn.lottery.model.Tab;
import neo.vn.lottery.model.TranDau;
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
    @BindView(R.id.tv_no_result)
    TextView tvNoResult;
    private TabAdapter adapterTab;
    private ArrayList<Tab> listTab;

    private ArrayList<SportItem> list;
    private SportAdapter adapter;
    private ArrayList<GiaiDau> listTour;
    private SportPresenter presenter;
    private Tab tabSelected;

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
        presenter.getListGiaiDau(tabSelected.getCode());
    }

    @Override
    public void getListGiaiDauError() {

    }

    @Override
    public void showListTranDau(ArrayList<TranDau> listTrabDau) {
        list.clear();
        list.addAll(groupByList(listTrabDau));
        if (list.isEmpty()){
            tvNoResult.setVisibility(View.VISIBLE);
            rcvSport.setVisibility(View.GONE);
        } else {
            adapter.notifyDataSetChanged();
            tvNoResult.setVisibility(View.GONE);
            rcvSport.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void getListTranDauError() {
        list.clear();
        adapter.notifyDataSetChanged();
    }

    public List<SportItem> groupByList(ArrayList<TranDau> list) {
        HashMap<String, List<TranDau>> hashMap = new HashMap<>();
        ArrayList<SportItem> result = new ArrayList<>();
        for (TranDau td : list) {
            if (!hashMap.containsKey(td.ngayDa)) {
                ArrayList<TranDau> values = new ArrayList<>();
                values.add(td);

                hashMap.put(td.ngayDa, values);
            } else {
                hashMap.get(td.ngayDa).add(td);
            }
        }

        for (Map.Entry<String, List<TranDau>> stringListEntry : hashMap.entrySet()) {
            Map.Entry me2 = stringListEntry;
            DateItem dateItem = new DateItem();
            dateItem.setSection(true);
            dateItem.setDate((String) me2.getKey());
            if (me2.getValue() != null)
                result.addAll((Collection<? extends SportItem>) me2.getValue());
        }
        return result;
    }

    @Override
    public void showListGiaiDau(ArrayList<GiaiDau> lstGiaiDau) {
        listTour.clear();
        listTour.addAll(lstGiaiDau);
        ArrayList<String> list = new ArrayList<>();
        for (GiaiDau gs : lstGiaiDau) {
            list.add(gs.name);
        }
        ArrayAdapter<String> adapterTour = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
        adapterTour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTour.setAdapter(adapterTour);
        spTour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getListTranDau(listTour.get(position).id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (list.size() > 0)
            spTour.setSelection(0);
    }

    private void initView() {
        listTour = new ArrayList<>();
        tabSelected = new Tab(getResources().getString(R.string.NHA), true, "ENG");
        listTab = new ArrayList<>();
        listTab.add(new Tab(getResources().getString(R.string.NHA), true, "ENG"));
        listTab.add(new Tab(getResources().getString(R.string.TBN), false, "SPAIN"));
        listTab.add(new Tab(getResources().getString(R.string.FRA), false, "FRA"));
        listTab.add(new Tab(getResources().getString(R.string.ITA), false, "ITA"));
        adapterTab = new TabAdapter(getContext(), listTab, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false);
        rcvTab.setLayoutManager(layoutManager);
        rcvTab.setAdapter(adapterTab);
        list = new ArrayList<>();
        adapter = new SportAdapter(getContext(), list, this);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rcvSport.setLayoutManager(layoutManager1);
        rcvSport.setAdapter(adapter);
    }

    @Override
    public void onSelect(Integer pos) {
        tabSelected = listTab.get(pos);
        presenter.getListGiaiDau(tabSelected.getCode());
    }

    @Override
    public void onSelectMatch(Integer pos) {

    }
}
