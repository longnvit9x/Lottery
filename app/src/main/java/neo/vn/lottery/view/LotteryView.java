package neo.vn.lottery.view;

import neo.vn.lottery.model.Lottery;

public interface LotteryView extends BaseView {
    void getLotterySuccess(Lottery lottery);

    void getLotteryError();
}
