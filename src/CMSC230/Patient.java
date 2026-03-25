package CMSC230;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
public class Patient {
    private PatientIdentity identity;
    private final UUID uniqueID;

    public PatientIdentity getIdentity(){return identity;}
    public UUID getUUID(){return uniqueID;}

    /**
     * constructor for the patient class
     * @param id - PatientIdentity - identity of the patient being created
     */
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

    /**
     * private constructor for the static makePatient method
     * @param id - PatientIdentity - identity of the patient being created
     * @param uid - UUID - unique id for the patient being created
     */
    private Patient(PatientIdentity id, UUID uid){
        uniqueID = uid;
        identity = id;

    }

    /**
     * creates a csv line based on the current Patient with the format 
     * @return - String - a string in the format {last name,first name,date of birth,UUID}
     */
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
        if (line == null){
            throw new IllegalArgumentException(
                "line cant be null"
            );
        }
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
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }catch(ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
            return null;
        }
    }
}
