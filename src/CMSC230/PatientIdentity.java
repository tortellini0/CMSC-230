package CMSC230;
import java.util.Date;
public class PatientIdentity {
    private Name name;
    private Date dateOfBirth;

    //accessors
    /**
     * gets the name of a patient
     * @return - Name - the Name object stored in a patient identity
     */
    public Name getName(){return name;}

    /**
     * gets the date of birth of a patient
     * @return - Date - the Date object stored in a patient identity
     */
    public Date getDateOfBirth(){return dateOfBirth;}

    /**
     * constructor for the PatientIdentity class
     * @param tempName - Name - the name of the patient
     * @param tempDateOfBirth - Date - the date of birth of the patient
     */
    public PatientIdentity(Name tempName, Date tempDateOfBirth){
        if (tempName == null){
            throw new IllegalArgumentException(
                "tempName cant be null"
            );
        }else if (tempDateOfBirth == null){
            throw new IllegalArgumentException(
                "tempDateOfBirth cant be null"
            );
        }else{
            name = tempName;
            dateOfBirth = tempDateOfBirth;
        }
    }

    public boolean match(PatientIdentity other){
        if (other == null){
            throw new IllegalArgumentException(
                "other cant be null"
            );
        }
        if (this.getName().match(other.getName()) && this.getDateOfBirth().equals(other.getDateOfBirth())){
            return true;
        }else {
            return false;
        }
    }
    public boolean isLessThan(PatientIdentity other){
        if (other == null){
            throw new IllegalArgumentException(
                "other cant be null"
            );
        }
        if (this.getName().isLessThan(other.getName())){
            return true;
        }else if (this.getName().match(other.getName())){
            if (this.getDateOfBirth().compareTo(other.getDateOfBirth()) < 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }


}
