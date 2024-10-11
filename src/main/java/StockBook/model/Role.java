package StockBook.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "id")
public class Role {

	 @Id
	    @Column
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id; 
	    @Column
	    private String name;

	    public Role() {
	    }

		public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    //********** RELATIONSHIPS **********

	    //a role can be occupied by several users.
	    @OneToMany(mappedBy = "role")
	    @JsonManagedReference
	    private List<Users> user;

	    //********* FOREIGN KEY METHODS ***********


	    public List<Users> getUsers() {
	        return user;
	    }

	    public void setUsers(List<Users> user) {
	        this.user = user;
	    }
}
