package filipivanov.kraken;

/**
 * Created by WakingBliss on 9/18/2015.
 */
public class MenuType  {

    int menuTypeId;
    String menuType;

    public MenuType (int menuTypeId, String menuType) {


        this.menuTypeId = menuTypeId;

        this.menuType = menuType;


    }

    @Override
    public String toString() {
        return menuType;
    }
}
