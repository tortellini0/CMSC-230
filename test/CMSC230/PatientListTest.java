package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class PatientListTest {


    @Test
    void testAddOrdered(){
        PatientList list = new PatientList(4);
        PatientIdentity p1ID = new PatientIdentity(new Name("a", "a"), new Date(1,3,3));
        Patient p1 = new Patient(p1ID);
        PatientIdentity p2ID = new PatientIdentity(new Name("a", "a"), new Date(1,4,4));
        Patient p2 = new Patient(p2ID);
        PatientIdentity p3ID = new PatientIdentity(new Name("b", "b"), new Date(1,3,3));
        Patient p3 = new Patient(p3ID);
        PatientIdentity p4ID = new PatientIdentity(new Name("c", "c"), new Date(1,3,3));
        Patient p4 = new Patient(p4ID);

        assertTrue(list.add(p2));
        assertTrue(list.add(p1));
        assertTrue(list.add(p4));
        assertTrue(list.add(p3));
        assertFalse(list.add(p4));
        //TODO after iterators are done test the validity of the logic by comparing index by index

    }
}
