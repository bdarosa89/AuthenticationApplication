
package authenticationsystem;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Employee {
  
    private String userName;
    private String userPass;
    private String userPosition;
    
    //hash the users password with MD5
    public String hashPass(String pass) throws NoSuchAlgorithmException{       
        String original = pass;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
      StringBuilder sb = new StringBuilder();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
        this.userPass = sb.toString(); 
        return userPass;             
    } 
   
    //match username and hashed pass to credentials file
    public boolean validateUser(String user,String pass ) throws IOException{
        File credFile;
        credFile = new File("src/authenticationsystem/credentials.txt");
        Scanner credData = new Scanner(credFile);
        String userCheck;
        String passCheck;
        userName = user;
        userPass = pass;
        while (credData.hasNextLine()){
        Scanner credLine = new Scanner(credData.nextLine());//iterate through lines
        userCheck = credLine.next();
        passCheck = credLine.next();
        userPosition = credLine.next();
        if (userCheck.equals(userName)&& passCheck.equals(userPass)){ 
            
            return true;
            }
        }
        return false;
    
    };
    
    //grab user position
    public String getUserPosition(){
    return  userPosition;
}
    
    //use user position to display the appropriate file
    public void getUserData () throws FileNotFoundException{
        if (this.getUserPosition().equals("admin")){
            File adminFile = new File("src/authenticationsystem/admin.txt");
            Scanner adminFileReader = new Scanner(adminFile);
            while (adminFileReader.hasNextLine()){
                System.out.println(adminFileReader.nextLine());
            }
        }
        if (this.getUserPosition().equals("zookeeper")){
            File zooKeeperFile = new File("src/authenticationsystem/zookeeper.txt");
            Scanner zooKeeperFileReader = new Scanner(zooKeeperFile);
            while (zooKeeperFileReader.hasNextLine()){
                System.out.println(zooKeeperFileReader.nextLine());
            }
            }
        if (this.getUserPosition().equals("veterinarian")){
            File veterinarianFile = new File("src/authenticationsystem/veterinarian.txt");
            Scanner veterinarianFileReader = new Scanner(veterinarianFile);
            while (veterinarianFileReader.hasNextLine()){
                System.out.println(veterinarianFileReader.nextLine());
            }
        }
    }
            }   

  