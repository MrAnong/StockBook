package StockBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.BusinessResponse;
import StockBook.model.Business;
import StockBook.model.Role;
import StockBook.repository.RoleRepository;
import jakarta.transaction.Transactional;


@Service
public class RoleService {

	@Autowired
    private RoleRepository roleRepository;
	
	 //1. to add a role
    @Transactional
    public Role addRole(Role role){
        return roleRepository.save(role);
    }
    
    @Transactional
    public String getRoleNameById(long fkRole) {
        Role role = roleRepository.findById(fkRole).orElse(null);
        return (role != null) ? role.getName() : null; // Return role name or null if not found
    }
}
