package StockBook.seedData;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import StockBook.model.Business;
import StockBook.model.Business_Category;
import StockBook.model.Inventory;
import StockBook.model.Product;
import StockBook.model.Product_Category;
import StockBook.model.Role;
import StockBook.model.Stores;
import StockBook.model.Users;
import StockBook.repository.RoleRepository;
import StockBook.repository.UsersRepository;
import StockBook.service.BusinessService;
import StockBook.service.Business_CategoryService;
import StockBook.service.InventoryService;
import StockBook.service.ProductService;
import StockBook.service.Product_CategoryService;
import StockBook.service.StoresService;
import StockBook.service.UsersService;

@Component
public class StockbookData implements CommandLineRunner{

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private Business_CategoryService business_CategoryService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private StoresService storesService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Product_CategoryService product_CategoryService;
	
	@Autowired
	private InventoryService inventoryService;
	
	public void initializeData() {
		
	//********** Role Data **********//
		
		   Role role1 = new Role();
	        role1.setName("Business_Owner");
	        roleRepository.save(role1);
	       
	        Role role2 = new Role();
	        role2.setName("Store_Manager");
	        roleRepository.save(role2);
	        
	        Role role3 = new Role();
	        role3.setName("Inventory_Manager");
	        roleRepository.save(role3);
	        
	        Role role4 = new Role();
	        role4.setName("Teller");
	        roleRepository.save(role4);
	        
	 //********** user data **********//
		
	       Users user1 = new Users();
	        user1.setFirstName("Efang");
	        user1.setMiddleName("Barry");
	        user1.setLastName("Anong");
	        user1.setEmail("anong@gmail.com");
	        user1.setPhoneNumber("652698938");
	        user1.setUsername("awiz69");
	        user1.setLocation("Yaounde");
	        user1.setPassword("123456789");
//	        user1.setStatus("Active");
//	        user1.setRole(roleRepository.findByName("Business_Owner"));
	        
	        usersService.register(user1);
	        
	        Users user2 = new Users();
	        user2.setFirstName("Bawack");
	        user2.setMiddleName("Bryan");
	        user2.setLastName("Bawack");
	        user2.setEmail("bawack@gmail.com");
	        user2.setPhoneNumber("999999999");
	        user2.setUsername("bathra1");
	        user2.setLocation("Yaounde");
	        user2.setPassword("987654321");
//	        user2.setStatus("Active");
//	        user2.setRole(roleRepository.findByName("Business_Owner"));
	        
	        usersService.register(user2);
	        
	        Users user3 = new Users();
	        user3.setFirstName("Anong");
	        user3.setMiddleName("Christal");
	        user3.setLastName("Nifu");
	        user3.setEmail("bchris@gmail.com");
	        user3.setPhoneNumber("999999");
	        user3.setUsername("bchris");
	        user3.setLocation("Yaounde");
	        user3.setPassword("987654321");
	        user3.setStatus("Active");
	        user3.setRole(role2);
	        
	        usersRepository.save(user3);
	        
	 //********** business category data **********//
		
		Business_Category category1 = new Business_Category();
		
		category1.setName("Pharmacy");
		category1.setDescription("A store that sells pharmaceutical products and drugs");
		
		business_CategoryService.addCategory(category1);
		
		Business_Category category2 = new Business_Category();
		
		category2.setName("Groceries Store");
		category2.setDescription("A medium sized store that sells a variety of food items");
		
		business_CategoryService.addCategory(category2);
		
		Business_Category category3 = new Business_Category();
		
		category3.setName("Fashion Store");
		category3.setDescription("a store that sells fashion and designer items");
		
		business_CategoryService.addCategory(category3);
		
	//********** business data **********//
		
		Business business1 = new Business();
		
		business1.setName("Santa Lucia");
		business1.setType("Super market");
		business1.setDescription("A supermarket that sells a whole variety of items from food to house old and appliances");
		business1.setFkCategory(2);
		business1.setFkOwner(1);
		
		businessService.addBusiness(business1);
		
		Business business2 = new Business();
		
		business2.setName("Dovv");
		business2.setType("Super market");
		business2.setDescription("A supermarket that sells a whole variety of items from food to house old and appliances");
		business2.setFkCategory(2);
		business2.setFkOwner(2);
		
		businessService.addBusiness(business2);
		
	//********** stores data **********
		
		Stores store1 = new Stores();
		
		store1.setName("Santa_Lucia_Mendong");
		store1.setTown("Yaounde");
		store1.setLocation("Mendong");
		store1.setFkBusiness(1);
		
		storesService.addStore(store1);
		
	//********** user account **********
		
		Users user4 = new Users();
		
		user4.setUsername("user4");
		user4.setPassword("mmooppaaoo");
		user4.setFkRole(2);
		user4.setFkStore(1);
		
		usersService.addAccount(user4);
		
		Users user5 = new Users();
		
		user5.setUsername("users5");
		user5.setPassword("uusseerr55");
		user5.setFkRole(3);
		user5.setFkStore(1);
		
		usersService.addAccount(user5);
		
	//********** product category data *********
		
		Product_Category pCategory1 = new Product_Category();
		
		pCategory1.setName("Stationary");
		pCategory1.setDescription("products for schools, office and workspaces");
		pCategory1.setFkStoreManager(4);
		
		product_CategoryService.addCategory(pCategory1);
		
Product_Category pCategory2 = new Product_Category();
		
		pCategory1.setName("groceries");
		pCategory1.setDescription("products for food items");
		pCategory1.setFkStoreManager(4);
		
		product_CategoryService.addCategory(pCategory2);
		
Product_Category pCategory3 = new Product_Category();
		
		pCategory1.setName("shoes");
		pCategory1.setDescription("products for footwear");
		pCategory1.setFkStoreManager(4);
		
		product_CategoryService.addCategory(pCategory3);
		
Product_Category pCategory4 = new Product_Category();
		
		pCategory1.setName("watches");
		pCategory1.setDescription("products for wrist watches");
		pCategory1.setFkStoreManager(4);
		
		product_CategoryService.addCategory(pCategory4);
		
		
	//********** product data **********
		
		Product product1 = new Product();
		
		product1.setName("blue bic");
		product1.setManufacturer("bic");
		product1.setOrigin("USA");
		product1.setDescription("A blue pen");
		product1.setCostPrice(50);
		product1.setUnitPrice(150);
		product1.setFkInventoryManager(5);
		product1.setFkCategory(1);
		
		productService.addProducts(product1);
		
	//********** inventory **********
		
//		Inventory inventory1 = new Inventory();
//		
//		inventory1.setQuantity(100);
//		inventory1.setFkProduct(1);
//		inventory1.setFkManager(4);
//		
//		inventoryService.updateInventory(inventory1);
		
		
		System.out.println("Stockbook data populated");
		
	}

	@Override
	public void run(String... args) throws Exception {
		initializeData();
		
	}
	
	
}
