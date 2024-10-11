package StockBook.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product_Category {

	 @Id
	    @Column
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    @Column
	    private String name;
	    @Column
	    private String descsription;
	    @Column
	    private long fkStoreManager;

	    public Product_Category() {
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

	    public String getDescsription() {
	        return descsription;
	    }

	    public void setDescsription(String descsription) {
	        this.descsription = descsription;
	    }

	    //********** RELATIONSHIPS **********


	    //one product category can be added by one and only one user(store manager)
	    @ManyToOne
	    @JoinColumn(name = "addedBy", referencedColumnName = "id", nullable=false)
	    @JsonBackReference
	    private Users storeManager;

	    //a product category can have several products for that category
	    @OneToMany(mappedBy = "productCategory")
	    @JsonManagedReference
	    private List<Product> productsList;

	    //********** FOREIGN KEY METHODS ************
}
