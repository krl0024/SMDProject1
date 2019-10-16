public interface IReceiptBuilder {
    public String appendHeader(String header);
    public String appendCustomer(CustomerModel customer);
    public String appendProduct(ProductModel product);
    public String appendPurchase(PurchaseModel purchase);
    public String appendFooter(String footer);

    public String toString();
}
