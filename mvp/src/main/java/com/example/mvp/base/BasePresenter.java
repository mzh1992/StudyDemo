package com.example.mvp.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView, CONTRACT> {

    protected M model;
    private WeakReference<V> vWeakReference;

    public BasePresenter() {
        model = getModel();
    }

    public abstract CONTRACT getContract();

    public abstract M getModel();

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    /**
     * 返回V
     *
     * @return
     */
    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    public void unbindView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }
}
