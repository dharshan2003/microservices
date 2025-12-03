package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.beans.User;
import com.example.repo.UserRepo;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux; // Use Flux for a stream of publishers

@Component
public class UserDataLoader {
    
    // Autowired field is sufficient, constructor injection is optional if using @Autowired on the field
    @Autowired
    private UserRepo repo;
    
    // Constructor injection is good practice, but you don't need the @Autowired annotation here
    public UserDataLoader(UserRepo repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void loadDummyData() {
        // Create the User objects
        User user1 = new User(1, "User1", "Address1");
        User user2 = new User(2, "User2", "Address2");
        User user3 = new User(3, "User3", "Address3");
        User user4 = new User(4, "User4", "Address4");
        
        // 1. Delete all existing data first (optional, but good for reliable dummy data loading)
        // 2. Use Flux.just() to create a stream of User objects
        // 3. Use flatMap to call the reactive save() method for each User
        // 4. Use then() to wait for all saves to complete before printing
        
        repo.deleteAll() // returns Mono<Void>
            .thenMany(Flux.just(user1, user2, user3, user4)) // Start a stream of User objects
            .flatMap(user -> repo.save(user)) // Call repo.save(user) for each User, which returns a Mono<User>
            .then() // Waits for the entire stream of saves to complete
            .doOnSuccess(v -> System.out.println("Dummy data loaded successfully!"))
            .subscribe(); // Triggers the execution of the reactive chain
    }
}