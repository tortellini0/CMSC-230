package CMSC230;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class NameTest {
    @Test
    void constructorThrowsForInvalidFirstName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Name(null, "last name");}
        );
        assertEquals("firstname cant be null",exception.getMessage());
    }
    @Test
    void constructorThrowsForInvalidLastName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Name("first name", null);}
        );
        assertEquals("lastname cant be null",exception.getMessage());
    }
}
