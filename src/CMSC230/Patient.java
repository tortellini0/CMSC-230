package CMSC230;
public class Patient {
    private PatientIdentity identity;

    public PatientIdentity getIdentity(){return identity;}
    
    public Patient(PatientIdentity id){
        if (id == null){
            throw new IllegalArgumentException(
                "id cant be null"
            );
        }else{

            identity = id;
        }
    }
}
