package CMSC230;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class HashTable {
    private String[] table;
    private int length;

    public HashTable(int lengthTemp){
        if (lengthTemp < 0){
            throw new IllegalArgumentException(
                "lengthTemp must be positive"
            );
        }
        length = lengthTemp;
        table = new String[length];
    }

    public boolean find(String contraIndication){
        if (contraIndication == null){
            throw new IllegalArgumentException(
                "contraIndication cant be null"
            );
        }
        int i = hash(contraIndication) % length;
        for (int n = i; table[n] != null;n++){
            if (table[n].matches(contraIndication)){
                return true;
            }else if (n == length - 1){
                return false;
            }
        }
        return false;
    }    

    public boolean add(String contraIndication){
        if (contraIndication == null){
            throw new IllegalArgumentException(
                "contraIndication cant be null"
            );
        }
        int i = hash(contraIndication) % length;
        System.out.println(i);
        // 0, 1, 2, 3, 4
        while (table[i] != null){
            System.out.println(i + "aaaa");
            if(i == (table.length - 1)){
                System.out.println("donedonedone");
                return false;
            }
            i++;
        }
        
        table[i] = contraIndication;
        return true;
    }

    private int hash(String meds){
        int hash = 0;
        char c1 = meds.charAt(0); 
        char c2 = meds.charAt(meds.length()/3);
        char c3 = meds.charAt(meds.length()*2/3);
        char c4 = meds.charAt(meds.length()-1);
        byte b1 = (byte) c1;
        byte b2 = (byte) c2;
        byte b3 = (byte) c3;
        byte b4 = (byte) c4;
        hash |= ((int) b1);
        hash |= ((int) b2) << 8;
        hash |= ((int) b3) << 16;
        hash |= ((int) b4 & 0x7f) << 24;
        return hash;
    }

    public boolean loadFromFile(String fileName){
        if (fileName == null){
            throw new IllegalArgumentException(
                "fileName cant be null"
            );
        }
        File file = new File(fileName);
        Scanner scan = null;
        boolean result = true;
        try{
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                String indication = scan.nextLine();
                if (indication != null){
                    add(indication);
                }
            }
        }catch(IOException e){
            //e.printStackTrace();
            result = false;
        }
        return result;
    }
}
