package main.java.com.example;

public class App {

public static void main(String[] args) throws Exception { 
    
    Calculator calc = new Calculator(); 

    UserService service = new UserService(); 

    service.findUser("admin"); 

    service.deleteUser("admin"); // NEW dangerous call 

} 
}

