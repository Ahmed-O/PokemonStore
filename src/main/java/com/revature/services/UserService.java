package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repos.UserRepo;
import com.revature.repos.ProductRepo;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ProductRepo prodRepo;

    public UserService(UserRepo userRepo, ProductRepo prodRepo) {
        this.userRepo = userRepo;
        this.prodRepo = prodRepo;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User register(User user){
        return userRepo.save(user);
    }
    
    public User findByEmail(String email){
        Optional<User> userOpt = userRepo.findByEmail(email);
        // if(userOpt.isPresent()) return userOpt.get();
        // else return null;
        return userOpt.orElse(null);
    }

    public boolean addToCart(User user, Product product){
        
        // Optional<Product> prodOpt = prodRepo.findByName("Bulbasaur");
        Optional<Product> prodOpt = prodRepo.findById(product.getId());
        Product dbProduct = prodOpt.orElse(null);

        System.out.println(dbProduct);
    
        List<Product> userCart = user.getCart();
        int cartQty = product.getQuantity();

        // If product already exists in cart add it to qty
        for(int i=0; i<userCart.size(); i++){
            if(product.getId() == userCart.get(i).getId()){
                cartQty += userCart.get(i).getQuantity();

                // can't add higher quantity to cart than is available
                if(cartQty > dbProduct.getQuantity()) return false;
                else return false;
            }
        }
        
        if(dbProduct==null) return false;
        else{
            //setting the correct quantity and other values 
            product.setName(dbProduct.getName());
            product.setQuantity(cartQty);
            product.setPrice(dbProduct.getPrice());
            product.setDescription(dbProduct.getDescription());
            product.setImage(dbProduct.getImage());
            product.setCountryCode(dbProduct.getCountryCode());
            
            user.getCart().add(product);
            userRepo.save(user);
            return true;
        }
    }

    public boolean orderCart(User user){
      
        List<Product> userCart = user.getCart();

        // Decrementing quantity of all the ordered products 
        for (int i = 0; i < userCart.size(); i++) {
            Product orderProduct = userCart.get(i);
           Product dbProduct = prodRepo.findById(orderProduct.getId()).orElse(null);

           if(dbProduct==null) return false;

           dbProduct.setQuantity(dbProduct.getQuantity()-orderProduct.getQuantity());
           prodRepo.save(dbProduct);
        }  

        // Emptying the cart 
        user.setCart(null);
        userRepo.save(user);

        return true;
    }



    
}
