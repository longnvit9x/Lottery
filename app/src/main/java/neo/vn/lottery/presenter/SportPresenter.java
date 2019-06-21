package neo.vn.lottery.presenter;

import neo.vn.lottery.view.SportView;

public class SportPresenter implements Presenter<SportView> {
    SportView view;

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

    public void getDataFromServer() {

    }
}