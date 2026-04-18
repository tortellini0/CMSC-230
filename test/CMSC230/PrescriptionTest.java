package CMSC230;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.PreconditionViolationException;

public class PrescriptionTest {

    @Test
    void constructerThrowsForInvalidTempMedicineName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Prescription(null,new Date(2000,1,12),12,"five");}
        );
        assertEquals("tempMedicineName cant be null", exception.getMessage());
    }

    @Test
    void constructerThrowsForInvalidTempDate(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Prescription("name",null,12,"five");}
        );
        assertEquals("tempDate cant be null", exception.getMessage());
    }

    @Test
    void constructerThrowsForInvalidTempPrescriber(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Prescription("name",new Date(2000,1,12),12,null);}
        );
        assertEquals("tempPrescriber cant be null", exception.getMessage());
    }

    @Test
    void moreRecentWorksCorrectly(){
        Date date1 = new Date(100, 6, 5);
        Date date2 = new Date(100, 7, 5);   
        Prescription p1 = new Prescription("1", date1, 1, "1");
        Prescription p2 = new Prescription("1", date2, 1, "1");
        assertTrue(Prescription.moreRecent(p2, p1));
        assertFalse(Prescription.moreRecent(p1, p2));
        assertTrue(Prescription.moreRecent(p1, null));
        assertFalse(Prescription.moreRecent(null, p1));
        assertFalse(Prescription.moreRecent(null, null));
    }

    @Test
    void matchWorks(){
        Date date1 = new Date(100, 6, 5);
        Date date2 = new Date(100, 6, 5);   
        Prescription p1 = new Prescription("1", date1, 1, "1");
        Prescription p2 = new Prescription("1", date1, 1, "1");
        Prescription p3 = new Prescription("4", date2, 1, "1");
        
        assertTrue(p1.match(p2));
        assertFalse(p1.match(p3));
    }

    @Test
    void matchThrowsForInvallidArgument(){

        Prescription p1 = new Prescription("name",new Date(2000,1,1),12,"five");
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {p1.match(null);}
        );
        assertEquals("other cant be null", exception.getMessage());
    }
    
    @Test
    void testValidLineForMake(){
        String validLine = "firstName,lastName,patient dob,medicineName,2022-01-01,4,prescriber";
        Prescription p = new Prescription("medicineName", new Date (122,0,1), 4, "prescriber");
        Prescription p1 = Prescription.make(validLine);
        assertTrue(p.match(p1));
    }

    @Test
    void testInvalidLineForMakeDateIsWrong(){
        String invalidLine = "firstName,lastName,patient dob,medicineName,2022/01-01,4,prescriber";
        assertNull(Prescription.make(invalidLine));
    }

    @Test
    void testInvalidLineForMakeNotEnoughTokens(){
        String invalidLine = "firstName,lastName,patient dob,medicineName,2022-01-01,4";
        assertNull(Prescription.make(invalidLine));
    
    }

    @Test
    void makeThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {Prescription.make(null);}
        );
        assertEquals("line cant be null", exception.getMessage());
    }

}
