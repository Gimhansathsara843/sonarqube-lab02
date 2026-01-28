package main.java.com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        
       logger.info(
                "Calculation result: {}",
                        new Calculator().calculate(10, 5, "add-again")
                    );

        UserService service = new UserService();
        service.findUser("admin");
        service.deleteUser("admin"); // NEW dangerous call
    }

}