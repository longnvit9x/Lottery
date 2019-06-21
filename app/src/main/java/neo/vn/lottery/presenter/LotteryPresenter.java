package neo.vn.lottery.presenter;


import neo.vn.lottery.view.LotteryView;

public class LotteryPresenter implements Presenter<LotteryView> {
    LotteryView view;

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

    public void getDataFromServer() {

    }
}
