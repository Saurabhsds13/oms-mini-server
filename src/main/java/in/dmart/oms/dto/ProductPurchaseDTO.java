package in.dmart.oms.dto;

public class ProductPurchaseDTO {

	private int productId;
	private int quantity;

	public ProductPurchaseDTO(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductPurchaseDTO [productId=" + productId + ", quantity=" + quantity + "]";
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
