
package authenticationsystem;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class AuthenticationSystem {

    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        
        Scanner scnr = new Scanner(System.in);
        int logAttempts = 0;
        String userName;
        String userPass;
        Employee currentUser = new Employee();
        boolean userLogout;
    //Compare user name & hashed password to credentials
    while (logAttempts <= 3){
        //Accept a username and password up to 3 times
        System.out.print("Username: ");
        userName = scnr.nextLine();
        System.out.print("Password: ");
        userPass = currentUser.hashPass(scnr.nextLine());//Hash the userPass
        
        //if login is successful retrieve file
        if (currentUser.validateUser(userName, userPass)==true){
            currentUser.getUserData();//grab appropriate file
            userLogout = false;
            while (!userLogout){
            System.out.println("\nType \"exit\" to quit.\nType \"logout\" to log out");
                switch (scnr.nextLine()) {//loop until logout or exit
                    case "logout":
                        userLogout = true;
                        break;
                    case "exit":
                        System.exit(0);
                }
            }           
        }
        else {
            System.out.println("Incorrect username or password.");
            logAttempts++;
            }
        
        //on the third failed attemp exit
        if (logAttempts == 3) {
            System.out.println("Login attempts exceeded. Exiting...");
            System.exit(0);
            }
        }
    }
}
    

