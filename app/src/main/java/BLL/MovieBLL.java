package BLL;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.perrusset.romain.boxotop.UIL.Model.Movie;

import java.util.ArrayList;
import java.util.List;

import BLL.Callback.CallbackMoviesAPI;
import SAL.ErrorMoviesAPIEnum;
import SAL.MoviesAPI;

public class MovieBLL {

    public static void getMoviesBoxOffice(Context context, final int page, final CallbackMoviesAPI.BoxOffice callback) {

        //Check if connection OK
        // YES
        if (hasNetworkConnection(context)) {
            //Get the info by API
           MoviesAPI api =  new MoviesAPI(context);

           ArrayList<Movie> test = api.getMoviesBoxOffice(page);
            //return result
            callback.onResultBoxOffice(test,page);

            // Get the details of the movies and save in BDD

        }
        //NO
        else {


            //Get by BDD

            //DEFAULT
            //Return ERROR
        }
    }

    private static boolean hasNetworkConnection(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
