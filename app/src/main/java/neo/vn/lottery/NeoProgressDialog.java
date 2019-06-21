package neo.vn.lottery;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class NeoProgressDialog extends ProgressDialog {
    public NeoProgressDialog(Context context) {
        super(context);
        this.setProgressStyle(R.style.PosDialog);
    }

    String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neo_dialog_progress);
        TextView txtTitle= this.findViewById(R.id.tv_title_content);
        txtTitle.setText(title);
        setCancelable(false);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
