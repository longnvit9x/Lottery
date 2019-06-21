package neo.vn.lottery.presenter;

import neo.vn.lottery.view.BaseView;

interface Presenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
