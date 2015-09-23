package filipivanov.kraken;

/**
 * Created by WakingBliss on 9/18/2015.
 */
public class Menu {

    MenuType menuType;
    String itemName, itemSize, itemDescription;
    int menuId;

    public Menu (int menuId, MenuType menuType, String itemName, String itemSize, String itemDescription){

        this.menuId = menuId;
        this.menuType = menuType;
        this.itemName = itemName;
        this.itemSize = itemSize;
        this.itemDescription = itemDescription;


    }

    @Override
    public String toString() {
        return itemName + " - " + itemSize + " - " + itemDescription;
    }
}
