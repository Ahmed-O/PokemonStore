package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.revature.services.UserService;
import com.revature.models.User;
import com.revature.models.Product;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000" }, allowCredentials = "true")
public class UserController {
    
    private final UserService userService;
   
    public UserController(UserService userService) {
        this.userService = userService;
    }

    
    // Registering new user
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        
        User tempUser = userService.findByEmail(user.getEmail());

        //if user already exists
        if(tempUser!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else{
            userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request){
      
        User tempUser = userService.findByEmail(user.getEmail());

        // if user exists
        if (tempUser != null) {

            //if passwords match
            if(tempUser.getPassword().equals(user.getPassword())){
                request.getSession().setAttribute("user", tempUser);
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } 
    }

    //User Logout
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        
        request.getSession().removeAttribute("user");
        return ResponseEntity.ok().build();
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User> > getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    // Getting products in logged in users cart
    @GetMapping("/cart")
    public ResponseEntity<List<Product>> getUserCart(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        // No logged in user
        if (user == null) {
            return ResponseEntity.status(401).build();
        } else {
            return ResponseEntity.status(200).body(user.getCart());
        }

    }

    // Add product to logged in users cart
    @PostMapping("/cart")
    public ResponseEntity<Void> addToCart(
            @RequestBody Product product,
            HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        
        // No logged in user
        if(user==null){
            return ResponseEntity.status(401).build();
        }
        else{
            boolean addedSuccess = userService.addToCart(user, product);

            if(addedSuccess) return ResponseEntity.status(200).build();
            else return ResponseEntity.status(409).build(); 
        }

    }


    // Order cart 
    @GetMapping("/cart/order")
    public ResponseEntity<List<Product>> orderCart(HttpServletRequest request) {
        
        User user = (User) request.getSession().getAttribute("user");

        // No logged in user
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        boolean orderSuccess = userService.orderCart(user);

        if(orderSuccess) return ResponseEntity.status(200).body(user.getCart());
        else return ResponseEntity.status(409).build();
    }

 


}
