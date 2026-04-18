package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PrescriptionListTest {

    @Test
    void addThrowsForInvalidArgument(){
        PrescriptionList list = new PrescriptionList();
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {list.add(null);}
        );
        assertEquals("pr cant be null", exception.getMessage());
    }

    @Test
    void testAddWithNothingInList(){
        PrescriptionList list = new PrescriptionList();
        Prescription p1 = new Prescription("medName", new Date(2000,12,30), 1, "prescriber");
        list.add(p1);
        list.initIteration();
        assertTrue(list.next() != null);
    }

    @Test
    void testAddWithOneItemInList(){
        PrescriptionList list = new PrescriptionList();
        Prescription p1 = new Prescription("medName", new Date(2000,12,30), 1, "prescriber");
        Prescription p2 = new Prescription("medName", new Date(2001,12,30), 1, "prescriber");
        list.add(p1);
        list.add(p2);
        list.initIteration();
        assertTrue(list.next().match(p2));
        assertTrue(list.next().match(p1));
        assertTrue(list.next() == null);
    }

    @Test
    void testAddPreservesOrder(){
        PrescriptionList list = new PrescriptionList();
        Prescription p1 = new Prescription("medName", new Date(2000,12,30), 1, "prescriber");
        Prescription p2 = new Prescription("medName", new Date(2001,12,30), 1, "prescriber");
        Prescription p3 = new Prescription("medName", new Date(2002,12,30), 1, "prescriber");
        Prescription p4 = new Prescription("medName", new Date(2003,12,30), 1, "prescriber");
        Prescription p5 = new Prescription("medName", new Date(2004,12,30), 1, "prescriber");
        list.add(p1);
        list.add(p2);
        list.add(p5);
        list.add(p3);
        list.add(p4);
        list.initIteration();
        assertTrue(list.next().match(p5));
        assertTrue(list.next().match(p4));
        assertTrue(list.next().match(p3));
        assertTrue(list.next().match(p2));
        assertTrue(list.next().match(p1));
        assertTrue(list.next() == null);
    }

    @Test
    void readFromFileThrowsForNullFileName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {PrescriptionList.readFromFile(null, new PatientList(10));}
        );
        assertEquals("fileName cant be null", exception.getMessage());
    }

    @Test
    void readFromFileThrowsForNullPatientList(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {PrescriptionList.readFromFile("fileName", null);}
        );
        assertEquals("listOfPatients cant be null", exception.getMessage());
    }

    @Test
    void testReadFromFileWorks(){
        PatientList pList = new PatientList(10);
        PatientIdentity id1 = new PatientIdentity(new Name("firstname1","lastname1"), new Date(2000,0,1));
        PatientIdentity id2 = new PatientIdentity(new Name("firstname2","lastname2"), new Date(2001,1,2));
        Patient p1 = new Patient(id1);
        Patient p2 = new Patient(id2);
        pList.add(p1);
        pList.add(p2);
        PrescriptionList.readFromFile("testPrescriptionList.csv", pList);
    }    
}
