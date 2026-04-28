package CMSC230;
import java.util.Date;
public class PatientIdentity implements Identity{
    private Name name;
    private Date dateOfBirth;
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

    /**
     * checks if this identity is the same as the other identity
     * @param other - Identity - the other identity that is being checked, must be a PatientIdentity to return true
     * @return - boolean - true for when the both identities are the same and false when they are different.
     */
    public boolean match(Identity other){
        if (other == null){
            throw new IllegalArgumentException(
                "other cant be null"
            );
        }else if ( !(other instanceof PatientIdentity) ){
            return false;
        } else{
            PatientIdentity pOther = (PatientIdentity) other;
            return this.getName().match(pOther.getName()) && this.getDateOfBirth().equals(pOther.getDateOfBirth());
        }
    }

    /**
     * checks if this identity should go before the other identity by name then date of birth
     * @param other - PatientIdentity - other identity being compared to
     * @return - boolean - true for when this identity goes before the other identity and flase for when this identity goes after the other identity
     */
    public boolean isLessThan(Identity other){
        if (other == null){
            throw new IllegalArgumentException(
                "other cant be null"
            );
        }else if(!(other instanceof PatientIdentity)){
            return false;
        }else{
            PatientIdentity pOther = (PatientIdentity) other;
            if (this.getName().isLessThan(pOther.getName())){
                return true;
            }else if (this.getName().match(pOther.getName())){
                if (this.getDateOfBirth().compareTo(pOther.getDateOfBirth()) < 0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }


}
