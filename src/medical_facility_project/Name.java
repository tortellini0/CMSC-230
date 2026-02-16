package medical_facility_project;

public class Name {
    private String firstName;
    private String lastName;

    //accesors
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}

    public Name(String firstname, String lastname){
        if (firstname == null){
            throw new IllegalArgumentException(
                "firstname cant be null"
            );
        }else if (lastname == null){
            throw new IllegalArgumentException(
                "lastname cant be null"
            );
        }else{
            this.firstName = firstname;
            this.lastName = lastname;
        }
        //TODO test
    }
    /**
     * gives the full name of the Name object 
     * @return - String - returns a string in the format "lastName, firstName"
     */
    public String fullName(){
        //TODO test
        return lastName + ", " + firstName;
    }
    /**
     * compares 2 names to see if they are the same
     * @param other - Name - the other Name object that is being compared
     * @return - boolean - true for other having the same name and false for other not having the same name 
     */
    public Boolean match(Name other){
        //TODO test
        if (other.getLastName().toLowerCase().equals(this.getLastName().toLowerCase()) && other.getFirstName().toLowerCase().equals(this.getFirstName().toLowerCase())){
            return true;
        }else{
            return false;
        }

    }

    /**
     * compares 2 Names to see whether this Name should go after other Name by using the String.compareTo() method
     * @param other - Name - other Name that we comparing this Name to
     * @return - boolean - returns true for This name should be before other name. returns false for this name should be after other Name
     */
    public boolean isLessThan(Name other){
        //TODO test
        if(other.fullName().toLowerCase().compareTo(this.fullName().toLowerCase()) > 0){
            return false;
        }else if (other.fullName().toLowerCase().compareTo(this.fullName().toLowerCase()) < 0){
            return true;
        }else{
            return false;
        }

    }


}
