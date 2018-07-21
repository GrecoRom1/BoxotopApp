package com.perrusset.romain.boxotop.UIL.Contracts;

public interface BaseContract {

    interface View<T extends Presenter>
    {
        void setPresenter(T presenter);
    }

    interface Presenter<I extends View> {
        void start(I View);
    }
}