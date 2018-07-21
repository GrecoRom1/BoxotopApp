package com.perrusset.romain.boxotop.UIL.BLL;

import com.perrusset.romain.boxotop.UIL.Movie;
import com.perrusset.romain.boxotop.UIL.SAL.OMDBapi;

import java.util.ArrayList;

public class MoviesBLL {

    public static ArrayList<Movie> GetListMovie(int page){

        ArrayList<Movie> result = new ArrayList<Movie>();
        result = OMDBapi.GetListMovie(page);

        return result;
    }
}
