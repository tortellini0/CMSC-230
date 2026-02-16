package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PatientIdentityTest {
    public PatientIdentity p1;
    public PatientIdentity p2;
    public PatientIdentity p3;
    public PatientIdentity p4;
    public PatientIdentity p5;
    public PatientIdentity p6;
    @BeforeEach
    void beforeEach(){
        p1 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,3,24));
        p2 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,2,13));
        p3 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,2,13));
        p4 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2009,1,13));
        p5 = new PatientIdentity(new Name("fname1", "a last name1"), new Date(2010,2,13));
        p6 = new PatientIdentity(new Name("fname2", "b last name2"), new Date(2010, 1,15));
    }
    @Test
    void constructerThrowsForInvalidTempName(){
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
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {p1.match(null);}
        );
        assertEquals("other cant be null", exception.getMessage());
    }

    @Test
    void testMatchForValidLogic(){
        assertTrue(p2.match(p3));
        assertFalse(p2.match(p5));
    }

    @Test
    void isLessThanThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {p2.isLessThan(null);}
        );
        assertEquals("other cant be null", exception.getMessage());
    }

    @Test
    void isLessThanLogicWorksCorrectly(){
        assertTrue(p5.isLessThan(p6));
        assertTrue(p2.isLessThan(p1));
        assertFalse(p2.isLessThan(p4));
        assertFalse(p6.isLessThan(p1));

    }
}
