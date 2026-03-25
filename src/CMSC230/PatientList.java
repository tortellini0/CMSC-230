package CMSC230;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class PatientList {
    private Patient[] patientList;
    private final int maxPatients;
    private int patientAmount;
    private int indexOfIteration = -1;

    public int getPatientAmount(){return patientAmount;};
    /**
     * constructor for the PatientList class
     * @param max - int - signifies the max amount of patients that can be stored in the PatientList
     */
    public PatientList(int max){
        if (max <= 0){
            throw new IllegalArgumentException(
                "max must be greater than 0"
            );
        }
        maxPatients = max;
        patientList = new Patient[maxPatients];
        patientAmount = 0;
    }


    /**
     * adds a patient to the patientList while maintaining the sort of name then date of birth
     * @param patient - Patient - patient that is being added
     * @return - boolean - returns true for a successful add but false for an unsuccessful add
     */
    public boolean add(Patient patient){
        return addOrdered(patient);
    }

    /**
     * adds a patient to the patientList while maintaining the sort of name then date of birth
     * @param patient - Patient - patient that is being added
     * @return - boolean - returns true for a successful add but false for an unsuccessful add
     */
    private boolean addOrdered(Patient patient){
        if (patient == null){
            throw new IllegalArgumentException(
                "patient cant be null"
            );
        }
        int currentIndex = patientAmount - 1;
        if(patientAmount == maxPatients){
            return false;
        }else if (patientAmount == 0){
            patientList[0] = patient;
            patientAmount++;
            return true;
        }else {
            while (currentIndex >= 0 && patient.getIdentity().isLessThan(patientList[currentIndex].getIdentity())){
                patientList[currentIndex+1] = patientList[currentIndex];
                currentIndex--;
            }
            patientAmount++;
            patientList[currentIndex+1] = patient;
            return true;
        }
    }

    /**
     * uses binary search to find a patient using a patient identity
     * @param id - PatientIdentity - the identity of the patient that is being found
     * @return - Patient - returns the Patient if it is found and null if it is not found
     */
    public Patient find(PatientIdentity id){
        return binarySearch(id);
    }

    /**
     * uses binary search to find a patient using a patient identity
     * @param id - PatientIdentity - the identity of the patient that is being found
     * @return - Patient - returns the Patient if it is found and null if it is not found
     */
    private Patient binarySearch(PatientIdentity id){
        if (id == null){
            throw new IllegalArgumentException(
                "id cant be null"
            );
        }
        int lower = 0;
        int upper = patientAmount - 1;
        int mid = (upper + lower)/2;
        while (!id.match(patientList[mid].getIdentity()) && (lower < upper)){
            if(id.isLessThan(patientList[mid].getIdentity())){
                upper = mid - 1;
            }else{
                lower = mid + 1;
            }
            mid = (lower + upper)/2;
        }
        if(patientList[mid].getIdentity().match(id)){
            return patientList[mid];
        }else{
            return null;
        }
    }

    /**
     * initiates the iterator
     */
    public void initIteration(){
        indexOfIteration = 0;
    }

    /**
     * uses the indexOfIteration to return the patient at that index of patientList
     * @return - Patient - the patient that was at the indexOfIteration index
     */
    public Patient next(){
        if (indexOfIteration == -1){
            return null;
        }else if ( (indexOfIteration >= maxPatients) || (patientList[indexOfIteration] == null) ){
            indexOfIteration = -1;
            return null;
        }else{
            return patientList[indexOfIteration++];
        }
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
            e.printStackTrace();
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
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
