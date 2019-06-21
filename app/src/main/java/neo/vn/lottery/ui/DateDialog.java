package neo.vn.lottery.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import neo.vn.lottery.TimeUtils;

public class DateDialog extends DialogFragment {
    private static final String TIME = "TIME";
    private OnDateClickListener onDateClickListener;

    public static DateDialog newInstance(Long time) {
        DateDialog dialog = new DateDialog();
        Bundle bundle = new Bundle();
        bundle.putLong(TIME, time);
        dialog.setArguments(bundle);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Long time = getArguments().getLong(TIME, TimeUtils.dateNow().getTimeInMillis());
        Calendar c = TimeUtils.today();
        c.setTime(TimeUtils.getDateByTime(time));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener listener = (datePicker, year1, month1, dayOfMonth) -> {
            dismiss();
            onDateClickListener.onDateSet(datePicker, year1, month1, dayOfMonth);
        };
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);
        Calendar today = TimeUtils.today();
        today.add(Calendar.DAY_OF_MONTH, 1);
        dialog.getDatePicker().setMaxDate(today.getTimeInMillis());
        return dialog;
    }

    public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
        this.onDateClickListener = onDateClickListener;
    }

    interface OnDateClickListener {
        void onDateSet(DatePicker datePicker, int i, int i1, int i2);
    }
}
