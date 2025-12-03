package com.example.repo;
import org.springframework.data.r2dbc.repository.ReactiveCrudRepository; 
import org.springframework.stereotype.Repository;
import com.example.beans.User;

@Repository
public interface UserRepo extends ReactiveCrudRepository<User, Integer> {
    // Spring will automatically implement CRUD methods returning Mono/Flux
    // e.g., Mono<User> findById(Integer id);
}