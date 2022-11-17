import java.util.*;

public class database {

    public static String[] itemAisle = { "A1", "E1", "B1", "M1", "B2", "C1", "C2", "T1", "S1", "F1" };

    public static List<String> find_item_by_id(List<item> list) {
        // initialize variables
        HashMap<Integer, String> data = new HashMap<Integer, String>();
        List<String> foundLocales = new ArrayList<String>();

        // initialize database with current values
        for (int i = 0; i < itemAisle.length; i++) {
            data.put(i, itemAisle[i]);
        }

        // check for items
        
        if(list == null){
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            String currentLocale = data.get(list.get(i).id);
            if (currentLocale == null) {
                foundLocales.add("");
            } else {
                foundLocales.add(currentLocale);
            }
        }

        return foundLocales;

    }

}
