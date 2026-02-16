package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

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

}
