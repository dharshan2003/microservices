package com.example.beans;

import java.util.List;

// Import R2DBC-specific annotations
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient; 
import org.springframework.data.relational.core.mapping.Table;

import lombok.NoArgsConstructor;
import lombok.Getter; // Use specific annotations instead of just @Data
import lombok.Setter;
import lombok.EqualsAndHashCode; 
import lombok.ToString;

@Table("users")
@Getter // Provides all getters
@Setter // Provides all setters
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false) // Ensures equals/hashCode are generated without superclass confusion
@ToString // Generates a good toString() method
public class User {
    
    @Id
    private Integer userId;
    private String userName;
    private String userAddr;
    
    @Transient
    private List<Contact> contacts;
    
    // Explicit constructor 1 (for R2DBC/Saving)
    public User(Integer userId, String userName, String userAddr) {
        this.userId = userId;
        this.userName = userName;
        this.userAddr = userAddr;
    }

    // Explicit constructor 2 (for Microservice response)
    public User(Integer userId, String userName, String userAddr, List<Contact> contacts) {
        this(userId, userName, userAddr); // Calling the base constructor
        this.contacts = contacts;
    }
}