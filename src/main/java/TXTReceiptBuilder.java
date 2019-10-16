public class TXTReceiptBuilder implements IReceiptBuilder {

  //  StringBuilder sb = new StringBuilder();

    @Override
    public String appendHeader(String header) {
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\r\n");
        return sb.toString();
    }

    @Override
    public String appendCustomer(CustomerModel customer) {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer ID: ").append(customer.mCustomerID).append("\r\n");
        sb.append("Customer Name: ").append(customer.mName).append("\r\n");
        sb.append("Customer Phone: ").append(customer.mPhone).append("\r\n");
        sb.append("Customer Address: ").append(customer.mAddress).append("\r\n\n");
        return sb.toString();
    }

    @Override
    public String appendProduct(ProductModel product) {
        StringBuilder sb = new StringBuilder();
        sb.append("Product ID: ").append(product.mProductID).append("\r\n");
        sb.append("Product Name: ").append(product.mName).append("\r\n");
        sb.append("Product Price: ").append(product.mPrice).append("\r\n");
        sb.append("Product Tax: ").append(product.mTax).append("\r\n");
        sb.append("Product Quantity: ").append(product.mQuantity).append("\n\n");
        return sb.toString();
    }

    @Override
    public String appendPurchase(PurchaseModel purchase) {
        StringBuilder sb = new StringBuilder();
        sb.append("Purchase ID: ").append(purchase.mPurchaseID).append("\r\n");
        sb.append("\nCustomer ID: ").append(purchase.mCustomerID).append("\r\n");
        sb.append("\nProduct ID: ").append(purchase.mProductID).append("\r\n");
        sb.append("\nPurchase Date: ").append(purchase.mDate).append("\r\n");
        sb.append("\nPurchase Name: ").append(purchase.mPName).append("\r\n");
        sb.append("\nPurchase Quantity: ").append(purchase.mQuantity).append("\r\n");
        sb.append("\nPurchase Price: ").append(purchase.mPrice).append("\r\n");
        sb.append("\nPurchase Tax: ").append(purchase.mTax).append("\r\n");
        sb.append("\nPurchase Total: ").append(purchase.mTotal).append("\r\n");

        return sb.toString();
    }

    @Override
    public String appendFooter(String footer) {
        StringBuilder sb = new StringBuilder();
        sb.append(footer).append("\r\n");
        return sb.toString();
    }
}
