package com.perrusset.romain.boxotop.UIL.Contracts;

public interface BoxOfficeContract extends BaseContract {

    interface View<T extends Presenter> extends BaseContract.View {
        void notifiyPresenterReady();
        void notifyDataLoaded();
        void startNextActivity(int movieID);
    }

    interface Presenter<I extends View> extends BaseContract.Presenter {
        void loadMoreData();
        void movieCardClicked(int movieID);
    }
}
