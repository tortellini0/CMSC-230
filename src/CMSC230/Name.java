package CMSC230;

public class Name {
    private String firstName;
    private String lastName;

    //accesors
    /**
     * accessor for the first name
     * @return - String - first name
     */
    public String getFirstName(){return firstName;}

    /**
     * accessor for the last name
     * @return - String - last name
     */
    public String getLastName(){return lastName;}

    /**
     * constructor for the Name class
     * @param firstname - String - first name
     * @param lastname - String - last name
     */
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
    }

    /**
     * gives the full name of the Name object 
     * @return - String - returns a string in the format "lastName, firstName"
     */
    public String fullName(){
        return lastName + ", " + firstName;
    }

    /**
     * compares 2 names to see if they are the same
     * @param other - Name - the other Name object that is being compared
     * @return - boolean - true for other having the same name and false for other not having the same name 
     */
    public Boolean match(Name other){
        if (other == null){
            throw new IllegalArgumentException(
                "other cant be null"
            );
        }
        boolean condition1 = other.getLastName().toLowerCase().equals(this.getLastName().toLowerCase());
        boolean condition2 = other.getFirstName().toLowerCase().equals(this.getFirstName().toLowerCase());
        if (condition1 && condition2){
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
        if (other == null){
            throw new IllegalArgumentException(
                "other cant be null"
            );
        }
        if(other.fullName().toLowerCase().compareTo(this.fullName().toLowerCase()) > 0){
            return true;
        }else if (other.fullName().toLowerCase().compareTo(this.fullName().toLowerCase()) < 0){
            return false;
        }else{
            return false;
        }

    }


}
