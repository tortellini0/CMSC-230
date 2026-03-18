package CMSC230;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public String toCSV(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        PatientIdentity identity = getIdentity();
        String name = identity.getName().fullName();
        String date = format.format(identity.getDateOfBirth());
        return name + "," + date;
    }

    public static Patient makePatient(String line){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //token {last name, first name, date of birth}
        String[] tokens = line.split(",");
        PatientIdentity identity;

        try{
            identity = new PatientIdentity(new Name(tokens[1],tokens[0]), format.parse(tokens[2]));
            return new Patient(identity);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
