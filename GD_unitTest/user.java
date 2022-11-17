import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class user {
    public static String[] itemNames = { "apples", "eggs", "bread", "milk", "butter", "cheese", "coffee", "tea", "sugar", "flour" };
    public List<item> list = null;

    public List<item> getCart() {

        List<item> shoppingList = new ArrayList<item>();

        for (int i = 0; i < itemNames.length; i++) {

            shoppingList.add(new item(i, itemNames[i], 1));

        }

        return shoppingList;

    }

    @Test
    public void findItemsExpectedvsActual_P() {
        // initialize
        List<String> expectedLocales = Arrays.asList("A1", "E1", "B1", "M1", "B2", "C1", "C2", "T1", "S1", "F1");
        List<item> list = getCart();
        List<String> itemLocations = database.find_item_by_id(list);
        
        //testlo9 ..0
        assertEquals(expectedLocales, itemLocations);

    }

    @Test
    public void findItemsExpectedvsActual_F(){
         // initialize
         //incorrect locales app side (database is never wrong)
         List<String> expectedLocales = Arrays.asList("A0", "E1", "B1", "M1", "B2", "C1", "C2", "T1", "S1", "F1");
         List<item> list = getCart();
         List<String> itemLocations = database.find_item_by_id(list);
         
         //test
         assertNotEquals(expectedLocales, itemLocations);
    }

    @Test
    public void findItemsByIDItemNoItemLocale() {
        // initialize
        List<String> expectedLocales = Arrays.asList("A1", "E1", "B1", "M1", "B2", "C1", "C2", "T1", "S1", "F1");
        List<item> list = getCart();
        int listCurrSize = list.size();
        list.add(new item(listCurrSize+1, "chips", 1));
        List<String> itemLocations = database.find_item_by_id(list);
        
        assertNotEquals(expectedLocales, itemLocations);

    }

    
    @Test
    public void findItemsNullInput(){

        //test
        //null list from user
        assertNull(database.find_item_by_id(list));

     }


}
