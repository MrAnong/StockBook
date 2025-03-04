package StockBook.model;

import java.time.LocalDateTime;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class, 
	    property = "id"
	)
public class Business {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private String status;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private long fkCategory;
    @Column
    private long fkOwner;

    public Business() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //********** RELATIONSHIPS **********

    //a business can be owned by one and only one user(business owner)
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "business-user")
    private Users businessOwner;

    //a business can have several sub branches(stores)
    @OneToMany(mappedBy = "business")
    @JsonManagedReference(value = "store-business")
    private List<Stores> stores;

    //a business can belong to one and only one business category
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "business-category")
    private Business_Category businessCategory;

    //********** FOREIGN KEY METHODS **********


    public Users getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(Users businessOwner) {
        this.businessOwner = businessOwner;
    }

    public List<Stores> getStores() {
        return stores;
    }

    public void setStores(List<Stores> stores) {
        this.stores = stores;
    }

    public Business_Category getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(Business_Category businessCategory) {
        this.businessCategory = businessCategory;
    }

	public long getFkCategory() {
		return fkCategory;
	}

	public void setFkCategory(long fkCategory) {
		this.fkCategory = fkCategory;
	}

	public long getFkOwner() {
		return fkOwner;
	}

	public void setFkOwner(long fkOwner) {
		this.fkOwner = fkOwner;
	}
    
    
}
