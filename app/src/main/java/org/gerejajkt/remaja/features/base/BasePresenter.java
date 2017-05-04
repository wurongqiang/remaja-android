package org.gerejajkt.remaja.features.base;

/**
 * Created by huteri on 4/25/17.
 */

public class BasePresenter<T extends BaseView> {

    private T view;

    public T getView() {
        return view;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public void onAttachView(T view) {
        this.view = view;
    }

    public void onDetachView() {
        view = null;
    }

    public void onCreateView(T view) { this.view = view; }
}
