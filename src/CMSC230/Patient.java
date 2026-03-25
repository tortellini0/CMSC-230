package CMSC230;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
public class Patient {
    private PatientIdentity identity;
    private UUID uniqueID;

    public PatientIdentity getIdentity(){return identity;}
    public UUID getUUID(){return uniqueID;}


    public Patient(PatientIdentity id){
        if (id == null){
            throw new IllegalArgumentException(
                "id cant be null"
            );
        }else{
            uniqueID = UUID.randomUUID();
            identity = id;
        }
    }

    private Patient(PatientIdentity id, UUID uid){
        uniqueID = uid;

    }

    public String toCSV(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        PatientIdentity identity = getIdentity();
        Name fullName = identity.getName();
        String name = fullName.getLastName() + "," + fullName.getFirstName(); 
        String date = format.format(identity.getDateOfBirth());
        String uid = getUUID().toString();
        return name + "," + date + "," + uid;
    }
    /**
     * creates a patient from a csv line
     * @param line - String - line format {last name,first name,date of birth,UUID}.
     * @return - Patient - the patient that is created using 
     */
    public static Patient makePatient(String line){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] tokens = line.split(",");
        PatientIdentity identity;
        UUID uniqueID;
        try{
            uniqueID = UUID.fromString(tokens[3]);
            identity = new PatientIdentity(new Name(tokens[1],tokens[0]), format.parse(tokens[2]));
            return new Patient(identity, uniqueID);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
