package com.nikesh.displaypostsexample.base;

public interface CommonNavigator {
    void handleError(Throwable throwable);

    void loadProgressBar(boolean showProgress);
}
