package neo.vn.lottery.presenter;


import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import neo.vn.lottery.TimeUtils;
import neo.vn.lottery.api.ApiRequest;
import neo.vn.lottery.api.Config;
import neo.vn.lottery.model.Lottery;
import neo.vn.lottery.view.LotteryView;

public class LotteryPresenter implements Presenter<LotteryView> {
    LotteryView view;
    private CompositeDisposable disposables = new CompositeDisposable();

    public void setView(LotteryView view) {
        this.view = view;
    }

    @Override
    public void attachView(LotteryView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void getDataFromServer(String tenMien, Date date) {
        String strDate = TimeUtils.formatDateToString(date, TimeUtils.DAY_MONTH_YEAR);
        Disposable disposable = ApiRequest.getInstance(Config.NO_AUTHORITY).getLottery(tenMien, strDate)
                .map(Lottery::convertFromEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(lottery -> {
                            view.getLotterySuccess(lottery);
                        },
                        ex ->
                        {
                            view.getLotteryError();
                        }
                );
        disposables.add(disposable);
    }
}
