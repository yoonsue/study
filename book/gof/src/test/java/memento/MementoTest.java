package memento;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.undo.StateEditable;

import java.util.Hashtable;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MementoTest {
    @ParameterizedTest
    @MethodSource("myStateEditTestArgs")
    void myStateEditTest(StateEditable object, boolean expected) {
        boolean result = false;

        MyStateEdit newEdit = new MyStateEdit(object);
        newEdit.end();

        System.out.println(newEdit.object.hashCode());


        Hashtable<Object,Object> hashtable = new Hashtable<Object, Object>(11);
        hashtable.put("key1", "value1");

        System.out.println(hashtable.hashCode());
        object.storeState(hashtable);

        System.out.println(newEdit.object.hashCode());
        newEdit.undo();

        System.out.println(newEdit.object.hashCode());

        if (hashtable.size() == 0) {
            result = true;
        }

        assertEquals(expected, result);
    }

    private static Stream<Arguments> myStateEditTestArgs() {

        StateEditable stateEditable = new StateEditable() {
            @Override
            public void storeState(Hashtable<Object, Object> state) {

            }

            @Override
            public void restoreState(Hashtable<?, ?> state) {

            }
        };

        return Stream.of(
                Arguments.of(stateEditable, true)
        );
    }

}