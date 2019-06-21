package neo.vn.lottery.view;

import java.util.ArrayList;

import neo.vn.lottery.model.GiaiDau;
import neo.vn.lottery.model.TranDau;

public interface SportView extends BaseView {
    void showListGiaiDau(ArrayList<GiaiDau> lstGiaiDau);

    void getListGiaiDauError();

    void showListTranDau(ArrayList<TranDau> list);

    void getListTranDauError();
}
