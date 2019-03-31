
package hu.bme.mit.train.controller;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TachoGraph {

    static Table<Long, Integer, Integer> googleGuavaTable = null;

    public static void insertRecord(long currentTimeMillis, int joystick, int speed) {
        if (googleGuavaTable == null)
            googleGuavaTable = HashBasedTable.create();
        googleGuavaTable.put(currentTimeMillis,joystick,speed);
    }

    public static Table<Long, Integer, Integer> getGoogleGuavaTable() {
        if (googleGuavaTable == null)
            googleGuavaTable = HashBasedTable.create();
        return googleGuavaTable;
    }
}
