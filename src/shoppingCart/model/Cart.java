package shoppingCart.model;




public class Cart {

	private Long cid;
	private String itemid;
	private String 	itemname;
	private String uid;
	private String Price;
	
	
	
	protected Cart() {
		
	}
	
	public Cart(long cid, String itemid, String itemname, String uid, String Price) {
		super();
		this.cid = cid;
		this.itemid = itemid;
		this.itemname = itemname;
		this.uid = uid;
		this.Price = Price;
		
		
	}

	public Cart(String itemid, String itemname, String uid, String Price) {
		super();
		this.itemid = itemid;
		this.itemname = itemname;
		this.uid = uid;
		this.Price = Price;
	}
	
	public Long getcid() {
		return cid;
	}

	public void setcid(Long cid) {
		this.cid = cid;
	}

	public String getItemId() {
		return itemid;
	}

	public void setItemId(String itemid) {
		this.itemid = itemid;
	}
	
	public String getItemName() {
		return itemname;
	}
	
	public void setItemName(String itemname) {
		this.itemname = itemname;
	}
	
	public String getuid() {
		return uid;
	}

	public void setuid(String uid) {
		this.uid = uid;
	}
	
	public String getPrice() {
		return Price;
	}

	public void setPrice(String Price) {
		this.Price = Price;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cid ^ (cid >>> 32));
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
		if (cid != other.cid)
			return false;
		return true;
	}
}
