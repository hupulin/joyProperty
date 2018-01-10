package com.joy.property.task.TimePickerDialog.listener;


import com.joy.property.task.TimePickerDialog.TimePickerDialog;
import com.joy.property.task.TimePickerDialog.TimePickerExpect;

import java.text.ParseException;

/**
 * Created by jzxiang on 16/4/20.
 */
public interface OnDateSetListener {

    void onDateSet(TimePickerDialog timePickerView, long millseconds) throws ParseException;
}
