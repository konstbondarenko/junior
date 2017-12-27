package ru.kbond.listinmap;

import java.util.HashMap;
import java.util.List;

/**
 * Class converts List to Map.
 * @author kbondarenko
 * @since 27.12.2017
 * @version 1
 */
public class UserConvert {
    /**
     * The returns HashMap<> of users converted from a List<> with an Id Assignment.
     * @return - HashMap<> of users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User us : list) {
            result.put(us.getId(), us);
        }
        return result;
    }
}
