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
public class PatientTest {

    @Test
    void constructerThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Patient(null);}
        );
        assertEquals("id cant be null", exception.getMessage());
    }

    @Test
    void testToCSV(){
        PatientIdentity p1I = new PatientIdentity(new Name("first","last"), new Date(107, 2, 12));
        Patient p1 = new Patient(p1I);
        String pUUID = p1.getUUID().toString();
        assertEquals("last,first,2007-03-12,"  + pUUID, p1.toCSV());
    }

    @Test
    void makePatientThrowsForInvalidArgumentException(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {Patient.makePatient(null);}
        );
        assertEquals("line cant be null", exception.getMessage());

    }
    
    @Test
    void testMakePatientDoesntLoseInfo(){
        PatientIdentity p1I = new PatientIdentity(new Name("first","last"), new Date(107, 2, 12));
        Patient p1 = new Patient(p1I);
        Patient p2;
        p2 = Patient.makePatient(p1.toCSV());
        assertTrue(p1.getIdentity().match(p2.getIdentity()));
    }

    @Test
    void testIncorectCSVlineForMakePatient(){
        UUID id1 = UUID.randomUUID();
        String csv1 = "last,first,2008/3/12," + id1.toString();
        String csv2 = "last,first,2008-3-12," + "2234";
        String csv3 = "last,first,2008-3-12";
        assertNull(Patient.makePatient(csv1));
        assertNull(Patient.makePatient(csv2));
        assertNull(Patient.makePatient(csv3));
    }

}
