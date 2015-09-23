package filipivanov.kraken;

/**
 * Created by WakingBliss on 9/14/2015.
 */
public interface Callback<T> {

    void done(T t);
}
