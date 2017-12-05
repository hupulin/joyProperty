package com.joy.property.asyntask;

/**
 * Created by Admin on 2014-12-25.
 */
public interface Feedback {
    public boolean isAvailable();

    public void start(CharSequence text);

    public void cancel(CharSequence text);

    public void success(CharSequence text);

    public void failed(CharSequence text);

    public void update(Object arg0);

    public void setIndeterminate(boolean indeterminate);
}
