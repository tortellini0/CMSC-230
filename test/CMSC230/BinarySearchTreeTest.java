package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;
public class BinarySearchTreeTest {

    @Test
    void testAddToTree(){
        BinarySearchTree tree = new BinarySearchTree();
        PatientIdentity identity = new PatientIdentity(new Name("first", "last"), new Date(1900,1,1));
        Patient patient = new Patient(identity);
        tree.add(patient);
        Patient testAdd = (Patient) tree.find(identity);
        assertTrue(testAdd.getIdentity().match(patient.getIdentity()));
    }

    @Test
    void addThrowsForInvalidArgument(){
        BinarySearchTree tree = new BinarySearchTree();
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {tree.add(null);}
        );
        assertEquals("object cant be null", exception.getMessage());
    }

    @Test
    void testFindInTree(){
        BinarySearchTree tree = new BinarySearchTree();
        PatientIdentity identity1 = new PatientIdentity(new Name("first", "last"), new Date(1900,1,1));
        PatientIdentity identity2 = new PatientIdentity(new Name("first", "last"), new Date(1903,1,1));
        PatientIdentity identity3 = new PatientIdentity(new Name("first", "last"), new Date(1902,1,1));
        PatientIdentity identity4 = new PatientIdentity(new Name("first", "last"), new Date(1906,1,1));
        PatientIdentity identity5 = new PatientIdentity(new Name("first", "last"), new Date(1909,1,1));
        PatientIdentity identity6 = new PatientIdentity(new Name("first", "last"), new Date(1929,1,1));
        Patient p1 = new Patient(identity1);
        Patient p2 = new Patient(identity2);
        Patient p3 = new Patient(identity3);
        Patient p4 = new Patient(identity4);
        Patient p5 = new Patient(identity5);
        tree.add(p1);
        tree.add(p3);
        tree.add(p5);
        tree.add(p2);
        tree.add(p4);
        assertTrue(tree.find(identity1).getIdentity().match(p1.getIdentity()));
        assertTrue(tree.find(identity2).getIdentity().match(p2.getIdentity()));
        assertTrue(tree.find(identity3).getIdentity().match(p3.getIdentity()));
        assertTrue(tree.find(identity4).getIdentity().match(p4.getIdentity()));
        assertTrue(tree.find(identity5).getIdentity().match(p5.getIdentity()));
        assertTrue(tree.find(identity6) == null);
    }

    @Test
    void findThrowsForInvalidArgument(){
        BinarySearchTree tree = new BinarySearchTree();
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {tree.find(null);}
        );
        assertEquals("identity cant be null", exception.getMessage());
    }
}
