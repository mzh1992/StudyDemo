package com.example.mvp.base;

public abstract class BaseModel<P extends BasePresenter, CONTRACT> {

    //Model需要将结果反馈给P层，因此需要P层的引用
    protected P p;

    public BaseModel(P p) {
        this.p = p;
    }

    //同时Model层也需要遵守契约，因此也需要实现契约
    public abstract CONTRACT getContract();

}
