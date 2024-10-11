package StockBook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


    Optional<Users> findByEmail(String email);
    
    Optional<Users> findByPhoneNumber(String phoneNumber);
    
    Optional<Users> findByUsername(String username);
//    
// // Method to find user by email or phone number
//    Optional<Users> findByEmailOrPhoneNumber(String email, String phoneNumber);
}

