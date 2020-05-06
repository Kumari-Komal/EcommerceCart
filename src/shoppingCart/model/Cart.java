package shoppingCart.model;

import java.time.LocalDate;


public class Cart {

	private Long id;
	private String 	ProductName;
	private String Price;
	private String description;
	private LocalDate targetDate;
	
	
	protected Cart() {
		
	}
	
	public Cart(long id, String ProductName, String Price, String description, LocalDate targetDate) {
		super();
		this.id = id;
		this.ProductName = ProductName;
		this.Price = Price;
		this.description = description;
		this.targetDate = targetDate;
		
	}

	public Cart(String ProductName, String Price, String description, LocalDate targetDate) {
		super();
		this.ProductName = ProductName;
		this.Price = Price;
		this.description = description;
		this.targetDate = targetDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return ProductName;
	}
	
	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}
	
	public String getPrice() {
		return Price;
	}

	public void setPrice(String Price) {
		this.Price = Price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
