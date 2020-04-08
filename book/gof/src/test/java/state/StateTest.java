package state;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {


    @Test
    void LockedCollectionTest() {
        Exception exception =  assertThrows(Exception.class, () -> { lockedCollectionTest(); });
        assertEquals("locked", exception.getMessage());
    }

    private void lockedCollectionTest() {
        Collection c = new ArrayList();
        LockedCollection lockedCollection = new LockedCollection(c);

        lockedCollection.iterator(); // state = locked

        Object o = new Object();
        lockedCollection.add(o);
    }

}