package DAL.DL;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.perrusset.romain.boxotop.UIL.Model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}