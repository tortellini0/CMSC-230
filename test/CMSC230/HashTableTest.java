package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
public class HashTableTest {
    @Test
    void constructerThrowsForInvalidArgument(){
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new HashTable(-1);}
        );
        assertEquals("lengthTemp must be positive", e.getMessage());
    }

    @Test
    void findThrowsForInvalidArgument(){
        HashTable table = new HashTable(10);
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {table.find(null);}
            );
            assertEquals("contraIndication cant be null", e.getMessage());
    }
    
    @Test
    void addThrowsForInvalidArgument(){
        HashTable table = new HashTable(10);
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {table.add(null);}
            );
            assertEquals("contraIndication cant be null", e.getMessage());
    }

    @Test
    void addAndFindFunctionality(){
        HashTable table = new HashTable(5);
        assertTrue(table.add("fifty five"));
        assertTrue(table.add("twelve"));
        assertTrue(table.add("thirty two"));
        assertTrue(table.add("one hundred and ten"));
        assertTrue(table.add("seventy seven"));
        assertFalse(table.add("fails"));
        
        assertTrue(table.find("fifty five"));
        assertTrue(table.find("twelve"));
        assertTrue(table.find("thirty two"));
        assertTrue(table.find("one hundred and ten"));
        assertTrue(table.find("seventy seven"));
        assertFalse(table.find("fails"));
    }

    @Test
    void loadFromFileWorks(){
        HashTable table = new HashTable(10);
        assertTrue(table.loadFromFile("contraindications.csv"));
        assertFalse(table.loadFromFile("<><><><><><><>??|}{"));
        assertTrue(table.find("med1, med2"));
    }

    @Test
    void loadFromFileThrowsForIllegalArgument(){
        HashTable table = new HashTable(10);
        Exception e = assertThrows(
            IllegalArgumentException.class, 
            () -> {table.loadFromFile(null);}
        );
        assertEquals("fileName cant be null", e.getMessage());
    }
}
