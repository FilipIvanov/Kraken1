package filipivanov.kraken;

/**
 * Created by WakingBliss on 9/16/2015.
 */
public class Payment   {

    int cardNumber,verificationNumber;
    String cardName,expirationDate;

    public Payment(int cardNumber, int verificationNumber,String cardName, String expirationDate){

        this.cardNumber = cardNumber;
        this.verificationNumber = verificationNumber;
        this.cardName = cardName;
        this.expirationDate = expirationDate;


    }
}
