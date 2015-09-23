package filipivanov.kraken;

/**
 * Created by WakingBliss on 8/5/2015.
 */
interface GetUserCallback {

    public abstract void done(User returnedUser);  // obavestava activity kada je server request gotov

}
