package com.perrusset.romain.boxotop.UIL.Contracts;

public interface BaseContract {

    interface View<T extends Presenter>
    {
        void setPresenter(T presenter);
    }

    interface Presenter {
        void start();
    }
}