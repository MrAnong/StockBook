package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.UsersResponse;
import StockBook.dto.utilities.UsersDetails;
import StockBook.model.Role;
import StockBook.model.Stores;
import StockBook.model.Users;
import StockBook.repository.RoleRepository;
import StockBook.repository.StoresRepository;
import StockBook.repository.UsersRepository;
import jakarta.transaction.Transactional;

import io.jsonwebtoken.Jwts;

@Service
public class UsersService {

	@Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository rolesRepository;
    
    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final String secretKey = "your_secret_key"; // Store securely
    
 

    @Transactional
    public UsersResponse register(Users user) {
        UsersResponse response = new UsersResponse();

        // Check if the email already exists
        Optional<Users> foundUser = usersRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            response.setMessage("Registration failed! Email already exists");
            response.setUser(null);
            return response;
        }

        // Check if the phone number already exists
        foundUser = usersRepository.findByPhoneNumber(user.getPhoneNumber());
        if (foundUser.isPresent()) {
            response.setMessage("Registration failed! Phone number already exists");
            response.setUser(null);
            return response;
        }

        // Check if the username already exists
        foundUser = usersRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            response.setMessage("Registration failed! Username already exists");
            response.setUser(null);
            return response;
        }

        // If no duplicates are found, proceed with registration
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Set user status and role
        user.setStatus("active");
        
        // Assuming you have a method to find the role by name
        Role role = rolesRepository.findByName("Business_Owner");
        user.setRole(role);

        // Save the user to the repository
        usersRepository.save(user);
        
        // Set foreign key role after saving
        user.setFkRole(user.getRole().getId());

        // Prepare the success response
        response.setMessage("Successful registration!");
        response.setUser(user);
        
        return response;
    }

    //2- to log in a user
    @Transactional
    public UsersResponse login(UsersDetails usersDetails) {
        UsersResponse response = new UsersResponse();
        
        // First, try to find the user by email
        Optional<Users> foundUser = usersRepository.findByEmail(usersDetails.getEmail_PhoneNumber_Username());
        
        // If not found by email, try to find by phone number
        if (foundUser.isEmpty()) {
            foundUser = usersRepository.findByPhoneNumber(usersDetails.getEmail_PhoneNumber_Username());
            if(foundUser.isEmpty()) {
            	foundUser = usersRepository.findByUsername(usersDetails.getEmail_PhoneNumber_Username());
            }
        }

        // Check if user is found
        if (foundUser.isEmpty()) {
            response.setMessage("Login failed! Invalid credentials");
            response.setUser(null);
            return response;
        }

        // Get the found user
        Users user = foundUser.get();
        
        // Check if the password matches
        boolean isPwdMatch = passwordEncoder.matches(usersDetails.getPassword(), user.getPassword());

        if (isPwdMatch) {
            response.setMessage("Login successful!");
            String token = user.generateJwtToken(secretKey);
            response.setUser(user);
            response.setToken(token);
        } else {
            response.setMessage("Login failed! Invalid credentials");
            response.setUser(null);
        }

        return response;
    }

    //3- to get a particuler user by id
    @Transactional
    public UsersResponse getById(Long id){
    	UsersResponse response = new UsersResponse();
        Optional<Users> foundUser = usersRepository.findById(id);
        if(foundUser.isEmpty()){
            response.setMessage("User not found");
            response.setUser(null);
            return response;
        }
        response.setMessage("User found");
        response.setUser(foundUser.get());
        return response;
    }

    //4- to get a particular user by email
    @Transactional
    public UsersResponse getByEmail(String email){
    	UsersResponse response = new UsersResponse();
        Optional<Users> foundUser = usersRepository.findByEmail(email);
        if(foundUser.isEmpty()){
            response.setMessage("User not found");
            response.setUser(null);
            return response;
        }
        response.setMessage("User found");
        response.setUser(foundUser.get());
        return response;
    }

    //5- to get a list of users
    @Transactional
    public List<Users> getList(List<Long> idList){
        List<Users> foundList = new ArrayList<>();
        for(Long id: idList){
            Optional<Users> foundUser = usersRepository.findById(id);
            if(foundUser.isPresent()){
                foundList.add(foundUser.get());
            }
        }
        return foundList;
    }

    //6- to get all users
    @Transactional
    public List<Users> getAll(){
        return usersRepository.findAll();
    }

    //7- to disactivate a particular user
    @Transactional
    public UsersResponse disactivate(Long id){
    	UsersResponse response = new UsersResponse();
        Optional<Users> foundUser = usersRepository.findById(id);
        if(foundUser.isEmpty()){
            response.setMessage("User not found");
            response.setUser(null);
            return response;
        }
        foundUser.get().setStatus("inactive");
        usersRepository.save(foundUser.get());

        response.setMessage("disactivated successfully");
        response.setUser(foundUser.get());
        return response;
    }


    //8- to disactivate a list of users
    @Transactional
    public List<Users> disactivateList(List<Long> idList){
        List<Users> responseList = new ArrayList<>();
        for(Long id: idList){
        	UsersResponse responseTest = disactivate(id);
            if(responseTest.getUser() != null){
                responseList.add(responseTest.getUser());
            }
        }
        return responseList;
    }

    //9- to change a user's role
    @Transactional
    public UsersResponse changeRole(Long userId, Long roleId){
    	UsersResponse response = new UsersResponse();
        Optional<Users> foundUser = usersRepository.findById(userId);
        if(foundUser.isEmpty()){
            response.setMessage("user not found");
            response.setUser(null);
            return response;
        }
        Optional<Role> foundRole = rolesRepository.findById(roleId);
        if(foundRole.isEmpty()){
            response.setMessage("Role not found");
            response.setUser(foundUser.get());
            return response;
        }
        foundUser.get().setRole(foundRole.get());
        usersRepository.save(foundUser.get());

        response.setMessage("role changed successfully");
        response.setUser(foundUser.get());
        return response;
    }

    //10- to modify a particular user
    @Transactional
    public UsersResponse updateUser(Users user){
        Optional<Users> foundUser = usersRepository.findById(user.getId());
        UsersResponse response = new UsersResponse();
        if(foundUser.isEmpty()) {
            response.setMessage("User not found");
            response.setUser(null);
            return response;
        }
        
      Users oldUser = foundUser.get();
      if(user.getFirstName() != null) {
    	  oldUser.setFirstName(user.getFirstName());
      }
      if(user.getMiddleName() != null) {
    	  oldUser.setMiddleName(user.getMiddleName());
      }
      if(user.getLastName() != null) {
    	  oldUser.setLastName(user.getLastName());
      }
      if(user.getEmail() != null) {
    	  oldUser.setEmail(user.getEmail());
      }
      if(user.getPhoneNumber() != null) {
    	  oldUser.setPhoneNumber(user.getPhoneNumber());
      }
      if(user.getUsername() != null) {
    	  oldUser.setUsername(user.getUsername());
      }
      if(user.getLocation() != null) {
    	  oldUser.setLocation(user.getLocation());
      }

        usersRepository.save(oldUser);
        response.setMessage("updated successfully");
        response.setUser(oldUser);
        return response;
    }
    
   //11- to add a user account to a store
    @Transactional
    public UsersResponse addAccount(Users user) {
    	UsersResponse response = new UsersResponse();
    	 
            Optional<Users> foundUser = usersRepository.findByUsername(user.getUsername());
             	if(foundUser.isEmpty()) {
                 user.setPassword(passwordEncoder.encode(user.getPassword()));
                 //TODO: after defining and creating all the roles in the db, find by id and return the role object for basic(client) users
          
                 
                 Optional<Role> role = rolesRepository.findById(user.getFkRole());
                 Optional<Stores> store = storesRepository.findById(user.getFkStore());
                 
                 user.setRole(role.get());
                 user.setStore(store.get());
                 user.setStatus("active");
                 
                 usersRepository.save(user);
                 
                 response.setMessage("successful registration!");
                 response.setUser(user);
                 return response;
             }else{
             	 response.setMessage("registration failed! Username already exists");
                  response.setUser(null);
                  return response;
             }
            
    }
    
    //3- to get a particuler user by id
    @Transactional
    public List<Users> getByStoreId(Long id){
        return usersRepository.findByFkStore(id);
    }
      
}
