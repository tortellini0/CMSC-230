package CMSC230;

import java.text.ParseException;
import java.util.Date;


import java.text.SimpleDateFormat;
public class Prescription {
    private String medicineName;
    private Date date;
    private int dosage;
    private String prescriber;

    public Date getDate(){return date;} 
    public String getName(){return medicineName;}
    public String getPrescriber(){return prescriber;}
    public int getDosage(){return dosage;}
    /**
     * creates a Prescription object using the arguments
     * @param tempMedicineName - String - name of the medicine
     * @param tempDate - Date - date of being prescribed the medicine
     * @param tempDosage - int - dosage of the medicine
     * @param tempPrescriber - String - who prescribed the medicine
     */
    public Prescription(String tempMedicineName, Date tempDate, int tempDosage, String tempPrescriber){
        if (tempMedicineName == null){
            throw new IllegalArgumentException(
                "tempMedicineName cant be null"
            );
        }else if (tempDate == null){
            throw new IllegalArgumentException(
                "tempDate cant be null"
            );
        }else if(tempPrescriber == null){
            throw new IllegalArgumentException(
                "tempPrescriber cant be null"
            );
        }else{
            medicineName = tempMedicineName;
            date = tempDate;
            dosage = tempDosage;
            prescriber = tempPrescriber;
        }
    }
    /**
     * checking to see if argument 1 is more recent than argument 2
     * @param p1 - Prescription - prescription to base return off of
     * @param p2 - Prescription - prescription to base return off of
     * @return - boolean - returns true if p1 is more recent than p2, otherwise returns false
     */
    public static boolean moreRecent(Prescription p1, Prescription p2){
        //arguments cant be more recent if they are both null
        if (p1 == null && p2 == null){
            return false;
        }
        //p2 will always be more recent than p1 if p1 is null
        if ((p1 == null) && (p2 != null)){
            return false;
        }
        //p1 is always more recent than a null
        if(p2 == null){
            return true;
        }
        int result = p2.getDate().compareTo(p1.getDate());
        return result < 0;
    }
    
    /**
     * compares 2 prescripion objects to see if they hold the same data
     * @param other - Prescription - the other prescription object we are comparing to
     * @return - boolean - true for if this Prescription matches the other Prescription, false if otherwise
     */
    public boolean match(Prescription other){
        if (other == null){
            throw new IllegalArgumentException(
                "other cant be null"
            );
        }
        boolean condition1 = (getDate().equals(other.getDate())) && (getName().equals(other.getName()));
        boolean condition2 = (getDosage() == other.getDosage()) && (getPrescriber().equals(other.getPrescriber()));
        return ( condition1 && condition2);
    }
    /**
     * creates a prescription with a csv line
     * @param line - String - csv line in the format of -> patient first name, patient last name, Patient DOB, medicine Name, date of issuing medicine, dosage, prescriber
     * @return - Prescription - returns the prescription object created by the line, returns null if there was a problem in making the object.
     */
    public static Prescription make(String line){
        if (line == null){
            throw new IllegalArgumentException(
                "line cant be null"
            );
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] tokens = line.split(",");
        Date tempDate;      
        String medName;
        int dose;
        String prescriberName;
        try{
            tempDate = format.parse(tokens[4]);
            medName = tokens[3];
            dose = Integer.parseInt(tokens[5]);
            prescriberName = tokens[6];
            return new Prescription(medName, tempDate, dose, prescriberName);

        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }catch(ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
            return null;
        }
    }
}
