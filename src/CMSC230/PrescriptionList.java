package CMSC230;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PrescriptionList {
    private ListItem head;
    private ListItem iterationItem = null;

    public PrescriptionList(){
        head = null;
    }

    //TODO test
    public void add(Prescription pr){
        if (pr == null){
            throw new IllegalArgumentException(
                "pr cant be null"
            ); 
        }
        ListItem itemAdd = new ListItem(pr);
        if(head == null){
            head = itemAdd;
        }else if(Prescription.moreRecent(itemAdd.data, head.data)){
            itemAdd.next = head;
            head = itemAdd;
        }else{
            ListItem itemCurrent = head;
            ListItem itemBefore = null;
            // 1,3,5,7,9
            //6
            while ( (Prescription.moreRecent(itemCurrent.data, itemAdd.data) )){
                itemBefore = itemCurrent;
                itemCurrent = itemCurrent.next;
            }
            itemAdd.next = itemCurrent;
            itemBefore.next = itemAdd;
        }
    }
    /**
     * initializes the iteration of the PrescriptionList
     */
    public void initIteration(){
        iterationItem = head;
    }
    
    /**
     * returns the next object in iteration 
     * @return - Prescription - returns the prescription object at the current iteration
     */
    public Prescription next(){
        if(iterationItem != null){
            Prescription temp = iterationItem.data;
            iterationItem = iterationItem.next;
            return temp;
        }else{
            return null;
        }
    }

    private class ListItem{
        public Prescription data;
        public ListItem next;


        public ListItem(Prescription pr){
            data = pr;
            next = null;
        }


    }
    /**
     * reads from a csv file to distribute various prescriptions to their respective patients
     * @param fileName - String - name of the file with all of the prescriptions
     * @param listOfPatients - PatientList - list of patients that we want to distrubute prescriptions to
     * @return - boolean - true for a successful run, false for an unsuccessful run
     */
    public static boolean readFromFile(String fileName, PatientList listOfPatients){
        
        if (fileName == null){
            throw new IllegalArgumentException(
                "fileName cant be null"
            );
        }else if (listOfPatients == null){
            throw new IllegalArgumentException(
                "listOfPatients cant be null"
            );
        }
        File file = new File(fileName);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scan = null;
        boolean result = true;
        String tempLine;
        String[] tokens;
        PatientIdentity identity;
        Patient tempPatient;
        Prescription temPrescription;
        try{
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                // this try and catch is if the DOB for the patient is invalid for the csv line
                try{
                    tempLine = scan.nextLine();
                    tokens = tempLine.split(","); 
                    identity = new PatientIdentity(new Name(tokens[0],tokens[1]), format.parse(tokens[2]));
                    tempPatient = listOfPatients.find(identity);
                    temPrescription = Prescription.make(tempLine);
                    if (temPrescription != null){
                        tempPatient.getPrescriptions().add(temPrescription);
                    }
                }catch(ParseException e){
                    e.printStackTrace();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
