package DAL.DL;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.perrusset.romain.boxotop.UIL.Model.Movie;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertOnlySingleMovie (Movie movies);
    @Insert
    void insertMultipleMovies (List<Movie> moviesList);

    @Query("SELECT * FROM Movie WHERE id = :movieId")
    Movie fetchOneMoviesbyMovieId (int movieId);

    @Update
    void updateMovie (Movie movies);
    @Delete
    void deleteMovie (Movie movies);
}
