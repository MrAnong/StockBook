package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.UsersResponse;
import StockBook.dto.utilities.UsersDetails;
import StockBook.model.Users;
import StockBook.service.UsersService;

@RestController
@RequestMapping("/stockbook/user/")
public class UserController {

	@Autowired
    private UsersService usersService;


    @PostMapping("register")
    public UsersResponse registration(@RequestBody Users user){
    	
    	System.out.println(user);
        return usersService.register(user);
    }

    @PostMapping("login")
    public UsersResponse login(@RequestBody UsersDetails userDetails){
        return usersService.login(userDetails);
    }

    @GetMapping("getid")
    public UsersResponse getById(@RequestParam Long id){
        return usersService.getById(id);
    }

    @GetMapping("getemail")
    public UsersResponse getByEmail(@RequestParam String email){
        return usersService.getByEmail(email);
    }

    @PostMapping("getlist")
    public List<Users> getList(@RequestBody List<Long> idList){
        return usersService.getList(idList);
    }

    @GetMapping("getall")
    public List<Users> getAll(){
        return usersService.getAll();
    }

    @GetMapping("disactivate")
    public UsersResponse disactivate(@RequestParam Long id){
        return usersService.disactivate(id);
    }

//    @PostMapping("disactivatelist")
//    public List<Users> disactivateList(@RequestBody List<Long> idList){
//        return usersService.disactivateList(idList);
//    }

    //TODO: test and implement endpoint when/if need be
    @PostMapping("setrole")
    public UsersResponse changeRole(@RequestParam Long userId, @RequestParam Long roleId){
        return usersService.changeRole(userId, roleId);
    }

    @PutMapping("updateuser")
    public UsersResponse updateUser(@RequestBody Users user){
        return usersService.updateUser(user);
    }
    
    @PostMapping("addUser")
    public UsersResponse addUser(@RequestBody Users user){
        return usersService.addAccount(user);
    }
    
    @PostMapping("getStoreId")
    public List<Users> getByStoreid(@RequestParam long id) {
    	return usersService.getByStoreId(id);
    }
}
