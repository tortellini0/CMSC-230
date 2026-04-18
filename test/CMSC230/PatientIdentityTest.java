package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;
public class PatientIdentityTest {

    @Test
    public void constructerThrowsForInvalidTempName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new PatientIdentity(null, new Date(2008,1,15));}
        );
        assertEquals("tempName cant be null", exception.getMessage());
    }

    @Test
    void constructerThrowsForInvalidTempDateOfBirth(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new PatientIdentity(new Name("fname", "lname"), null);}
        );
        assertEquals("tempDateOfBirth cant be null", exception.getMessage());
    }

    @Test
    void matchThrowsForInvalidArgument(){
        
        PatientIdentity p1 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,3,24));
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {p1.match(null);}
        );
        assertEquals("other cant be null", exception.getMessage());
    }

    @Test
    void testMatchForValidLogic(){
        PatientIdentity p1 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2010,2,13));
        PatientIdentity p2 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,2,13));
        PatientIdentity p3 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,2,13));
        assertTrue(p2.match(p3));
        assertFalse(p2.match(p1));
    }

    @Test
    void isLessThanThrowsForInvalidArgument(){
        PatientIdentity p1 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,2,13));
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {p1.isLessThan(null);}
        );
        assertEquals("other cant be null", exception.getMessage());
    }

    @Test
    void isLessThanLogicWorksCorrectly(){
        PatientIdentity p1 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,3,24));
        PatientIdentity p2 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,2,13));
        PatientIdentity p3 = new PatientIdentity(new Name("fname2", "b last name2"), new Date(2010, 1,15));
        PatientIdentity p4 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,1,13));
        PatientIdentity p5 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2010,2,13));
        assertTrue(p5.isLessThan(p3));
        assertTrue(p2.isLessThan(p1));
        assertFalse(p2.isLessThan(p4));
        assertFalse(p3.isLessThan(p1));

    }
}
