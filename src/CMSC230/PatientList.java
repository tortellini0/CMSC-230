package CMSC230;

public class PatientList {
    private Patient[] patientList;
    private final int maxPatients;
    private int patientAmount;
    public PatientList(int max){
        maxPatients = max;
        patientList = new Patient[maxPatients];
        patientAmount = 0;
    }

    // 0a,1b,2d,3d,3enull,null,null,null,null
    // d
    // cases no patient, full patient, mid patient 
    public boolean add(Patient patient){
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
    // id does not exist
    // beginning 
    // end
    // 1 item
    // 2 item
    // 3 item
    public Patient binarySearch(PatientIdentity id){
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
}
