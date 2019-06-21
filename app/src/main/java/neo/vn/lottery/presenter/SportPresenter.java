package neo.vn.lottery.presenter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import neo.vn.lottery.api.ApiRequest;
import neo.vn.lottery.api.Config;
import neo.vn.lottery.api.GiauDauEntity;
import neo.vn.lottery.api.TranDauEntity;
import neo.vn.lottery.model.GiaiDau;
import neo.vn.lottery.model.TranDau;
import neo.vn.lottery.view.SportView;

public class SportPresenter implements Presenter<SportView> {
    SportView view;
    private CompositeDisposable disposables = new CompositeDisposable();

    public void setView(SportView view) {
        this.view = view;
    }

    @Override
    public void attachView(SportView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void getListGiaiDau(String tenGiai) {
        Disposable disposable = ApiRequest.getInstance(Config.NO_AUTHORITY).getGiaiDau(tenGiai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(lstGiaiDau -> {
                            ArrayList<GiaiDau> list = new ArrayList<>();
                            for (GiauDauEntity gs : lstGiaiDau) {
                                list.add(GiaiDau.convertFromEntity(gs));
                            }
                            view.showListGiaiDau(list);
                        },
                        ex ->
                        {
                            view.getListGiaiDauError();
                        }
                );
        disposables.add(disposable);
    }

    public void getListTranDau(String id) {
        Disposable disposable = ApiRequest.getInstance(Config.NO_AUTHORITY).getListTranDau(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(lstGiaiDau -> {
                            ArrayList<TranDau> list = new ArrayList<>();
                            for (TranDauEntity gs : lstGiaiDau) {
                                list.add(TranDau.convertFromEntity(gs));
                            }
                            view.showListTranDau(list);
                        },
                        ex ->
                        {
                            view.getListTranDauError();
                        }
                );
        disposables.add(disposable);
    }
}