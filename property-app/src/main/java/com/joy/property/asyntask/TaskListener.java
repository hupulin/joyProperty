package com.joy.property.asyntask;

/**
 * Created by Admin on 2014-12-25
 */
public interface TaskListener {
    String getName();

    void onPreExecute(GenericTask task);

    void onPostExecute(GenericTask task, TaskResult result);

    void onProgressUpdate(GenericTask task, Object param);

    void onCancelled(GenericTask task);

}
