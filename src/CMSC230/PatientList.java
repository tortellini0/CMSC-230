package CMSC230;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class PatientList {
    private int patientAmount;
    private BinarySearchTree patientTree = new BinarySearchTree();

    public int getPatientAmount(){return patientAmount;};
    /**
     * constructor for the PatientList class
     */
    public PatientList(){
    }


    /**
     * adds a patient to the patientList while maintaining the sort of name then date of birth
     * @param patient - Patient - patient that is being added
     * @return - boolean - returns true for a successful add but false for an unsuccessful add
     */
    public void add(Patient patient){
        patientTree.add(patient);
    }

    

    /**
     * uses binary search to find a patient using a patient identity
     * @param id - PatientIdentity - the identity of the patient that is being found
     * @return - Patient - returns the Patient if it is found and null if it is not found
     */
    public Patient find(PatientIdentity id){
        if (id == null){
            throw new IllegalArgumentException(
                "id cant be null"
            );
        }
        return (Patient)patientTree.find(id);
    }


    /**
     * initiates the iterator
     */
    public void initIteration(){
        patientTree.initIteration();
    }

    /**
     * uses the indexOfIteration to return the patient at that index of patientList
     * @return - Patient - the patient that was at the indexOfIteration index
     */
    public Patient next(){
        return (Patient) patientTree.next();
    }

    /**
     * saves the current PatientList to a csv
     * @param fileName - String - file name for the csv
     * @return - boolean - true for successful file creation and false for unsuccessful
     */
    public boolean saveToFile(String fileName){
        if (fileName == null){
            throw new IllegalArgumentException(
                "fileName cant be null"
            );
        }
        File file = new File(fileName);
        boolean result = true;
        FileWriter writer = null;
        try{
            writer = new FileWriter(file);
            initIteration();
            Patient currentPatient = next();
            while (currentPatient != null){
                String line = currentPatient.toCSV();
                writer.write(line + "\n");
                currentPatient = next();
            }
            writer.close();
        }catch(IOException e){
            //e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * adds to the current PatientList from a pre existing file
     * @param fileName - String - name of the file that is being imported from 
     * @return - boolean - true for a successful import and false for an unsuccessful import
     */
    public boolean importFromFile(String fileName){
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
                Patient temp = Patient.makePatient(scan.nextLine());
                if (temp != null){
                    add(temp);
                }
            }
        }catch(IOException e){
            //e.printStackTrace();
            result = false;
        }
        return result;
    }
}
