package main.java.com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();
        logger.info("Calculation result: {}", calc.calculate(10, 5, "add-again"));
        
        UserService service = new UserService();
        service.findUser("admin");
        service.deleteUser("admin"); // NEW dangerous call
    }

}