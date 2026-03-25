package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class PatientListTest {

    @Test
    void constructorThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new PatientList(-1);}
        );
        assertEquals("max must be greater than 0", exception.getMessage());

    }

    @Test
    void testIterators(){
        PatientList list = new PatientList(3);
        PatientIdentity p1ID = new PatientIdentity(new Name("a", "a"), new Date(1,3,3));
        Patient p1 = new Patient(p1ID);
        PatientIdentity p2ID = new PatientIdentity(new Name("a", "a"), new Date(1,4,4));
        Patient p2 = new Patient(p2ID);
        PatientIdentity p3ID = new PatientIdentity(new Name("b", "b"), new Date(1,3,3));
        Patient p3 = new Patient(p3ID);
        list.add(p1);
        list.add(p2);
        list.add(p3);

        //tests that next returns null before being initiated
        assertTrue(list.next() == null);
        list.initIteration();

        //confirms that the iterator does get the correct value.
        assertTrue(p1.getIdentity().match(list.next().getIdentity()));
        assertTrue(p2.getIdentity().match(list.next().getIdentity()));
        assertTrue(p3.getIdentity().match(list.next().getIdentity()));
        assertTrue(list.next() == null);

    }

    @Test
    void addOrderedThrowsForInvalidArgument(){

        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new PatientList(5).add(null);}
        );
        assertEquals("patient cant be null", exception.getMessage());
    }

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
        
        //confirms that the patients are being added
        assertTrue(list.add(p2));
        assertTrue(list.add(p1));
        assertTrue(list.add(p4));
        assertTrue(list.add(p3));
        assertFalse(list.add(p4));

        //confirms that the list is sorted
        Patient[] confirmedList = {p1,p2,p3,p4};
        list.initIteration();
        for (int i = 0; i < 4; i++){
            assertTrue(confirmedList[i].getIdentity().match(list.next().getIdentity()));
        }
    }

    @Test
    void binarySearchThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new PatientList(5).find(null);}
        );
        assertEquals("id cant be null", exception.getMessage());
    }

    @Test
    void testBinarySearchWtih4Items(){    
        PatientList list1 = new PatientList(4);
        PatientIdentity p1ID = new PatientIdentity(new Name("a", "a"), new Date(1,3,3));
        Patient p1 = new Patient(p1ID);
        PatientIdentity p2ID = new PatientIdentity(new Name("a", "a"), new Date(1,4,4));
        Patient p2 = new Patient(p2ID);
        PatientIdentity p3ID = new PatientIdentity(new Name("b", "b"), new Date(1,3,3));
        Patient p3 = new Patient(p3ID);
        PatientIdentity p4ID = new PatientIdentity(new Name("c", "c"), new Date(1,3,3));
        Patient p4 = new Patient(p4ID);
        list1.add(p1);
        list1.add(p2);
        list1.add(p3);
        list1.add(p4);

        //testing with 4 patients
        assertTrue(p1ID.match(list1.find(p1ID).getIdentity()));
        assertTrue(p2ID.match(list1.find(p2ID).getIdentity()));
        assertTrue(p3ID.match(list1.find(p3ID).getIdentity()));
        assertTrue(p4ID.match(list1.find(p4ID).getIdentity()));
        assertTrue(list1.find(new PatientIdentity(new Name("1","2"), new Date(1,1,1))) == null);
        
    }

    @Test
    void testBinarySearchWith1Items(){
        PatientIdentity p1ID = new PatientIdentity(new Name("a", "a"), new Date(1,3,3));
        Patient p1 = new Patient(p1ID);
        PatientList list2 = new PatientList(1);
        list2.add(p1);
        assertTrue(p1ID.match(list2.find(p1ID).getIdentity()));
        assertTrue(list2.find(new PatientIdentity(new Name("1","2"), new Date(1,1,1))) == null);
    }
    @Test
    void testBinarySearchWith2Items(){
        PatientIdentity p1ID = new PatientIdentity(new Name("a", "a"), new Date(1,3,3));
        Patient p1 = new Patient(p1ID);
        PatientIdentity p2ID = new PatientIdentity(new Name("a", "a"), new Date(1,4,4));
        Patient p2 = new Patient(p2ID);
        //testing with 2 patients
        PatientList list3 = new PatientList(2);
        list3.add(p1);
        list3.add(p2);
        assertTrue(p1ID.match(list3.find(p1ID).getIdentity()));
        assertTrue(p2ID.match(list3.find(p2ID).getIdentity()));
        assertTrue(list3.find(new PatientIdentity(new Name("1","2"), new Date(1,1,1))) == null);
    }
        @Test
    void testBinarySearchWith3Items(){
        PatientIdentity p1ID = new PatientIdentity(new Name("a", "a"), new Date(1,3,3));
        Patient p1 = new Patient(p1ID);
        PatientIdentity p2ID = new PatientIdentity(new Name("a", "a"), new Date(1,4,4));
        Patient p2 = new Patient(p2ID);
        PatientIdentity p3ID = new PatientIdentity(new Name("b", "b"), new Date(1,3,3));
        Patient p3 = new Patient(p3ID);
        //testing with 3 patients
        PatientList list4 = new PatientList(3);
        list4.add(p1);        
        list4.add(p2);        
        list4.add(p3);        
        assertTrue(p1ID.match(list4.find(p1ID).getIdentity()));
        assertTrue(p2ID.match(list4.find(p2ID).getIdentity()));
        assertTrue(p3ID.match(list4.find(p3ID).getIdentity()));
        assertTrue(list4.find(new PatientIdentity(new Name("1","2"), new Date(1,1,1))) == null);
    }

    @Test
    void testSaveToFileAndReadFromFile(){
        PatientList list1 = new PatientList(4);
        PatientList list2 = new PatientList(5);
        PatientIdentity p1ID = new PatientIdentity(new Name("a", "a"), new Date(1,3,3));
        Patient p1 = new Patient(p1ID);
        PatientIdentity p2ID = new PatientIdentity(new Name("a", "a"), new Date(1,4,4));
        Patient p2 = new Patient(p2ID);
        PatientIdentity p3ID = new PatientIdentity(new Name("b", "b"), new Date(1,3,3));
        Patient p3 = new Patient(p3ID);
        PatientIdentity p4ID = new PatientIdentity(new Name("c", "c"), new Date(1,3,3));
        Patient p4 = new Patient(p4ID);
        list1.add(p1);
        list1.add(p2);
        list1.add(p3);
        list1.add(p4);
        assertTrue(list1.saveToFile("testSaveToFile.csv"));
        assertTrue(list2.importFromFile("testSaveToFile.csv"));
        
        list1.initIteration();
        list2.initIteration();
        Patient l1 = list1.next();
        Patient l2 = list2.next();
        while((l1 != null)||(l2 != null)){
            assertTrue(l1.getIdentity().match(l2.getIdentity()));
            l1 = list1.next();
            l2 = list2.next();
        }
    }

    @Test
    void saveToFileReturnsFalse(){
        PatientList list = new PatientList(5);
        assertFalse(list.saveToFile("notAfileName/>><<"));
    }

    @Test
    void saveToFileThrowsForInvalidArgumentException(){
        PatientList list = new PatientList(5);
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {list.saveToFile(null);}
        );
        assertEquals("fileName cant be null", exception.getMessage());
        
    }

    @Test
    void importFromFileThrowsForInvalidArgumentException(){
        
    }
}
