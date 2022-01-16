package models.usermodel;
public interface IUsersModel {

         String getEmailId();

         String getFirstName();

         String getLastName();

         long getContactNumber();

         String getPassword() ;

         String getType() ;

        void setEmailId(String emailId);

        void setFirstName(String firstName);

        void setLastName(String lastName);

        void setContactNumber(long contactNumber);

        void setPassword(String password);

        void setType(String type);

    }


