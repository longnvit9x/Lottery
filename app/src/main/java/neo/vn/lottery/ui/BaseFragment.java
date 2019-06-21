package neo.vn.lottery.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import neo.vn.lottery.NeoProgressDialog;
import neo.vn.lottery.R;
import neo.vn.lottery.view.BaseView;

public class BaseFragment  extends Fragment implements BaseView {
    private NeoProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new NeoProgressDialog(getContext());
    }


    @Override
    public void showProgress() {
        progressDialog.setTitle(this.getString(R.string.DEFAULT_TITLE_PROGRESS));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
