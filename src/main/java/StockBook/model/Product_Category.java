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
	    private String description;
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

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    //********** RELATIONSHIPS **********


	    public long getFkStoreManager() {
			return fkStoreManager;
		}

		public void setFkStoreManager(long fkStoreManager) {
			this.fkStoreManager = fkStoreManager;
		}

		public Users getStoreManager() {
			return storeManager;
		}

		public void setStoreManager(Users storeManager) {
			this.storeManager = storeManager;
		}

		public List<Product> getProductsList() {
			return productsList;
		}

		public void setProductsList(List<Product> productsList) {
			this.productsList = productsList;
		}

		//one product category can be added by one and only one user(store manager)
	    @ManyToOne
	    @JoinColumn(name = "addedBy", referencedColumnName = "id", nullable=false)
	    @JsonBackReference(value = "productCategory-user")
	    private Users storeManager;

	    //a product category can have several products for that category
	    @OneToMany(mappedBy = "productCategory")
	    @JsonManagedReference(value = "product-category")
	    private List<Product> productsList;

	    //********** FOREIGN KEY METHODS ************
}
