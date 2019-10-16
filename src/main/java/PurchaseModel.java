//package edu.auburn;

import java.util.Date;

public class PurchaseModel {
    public int mPurchaseID, mCustomerID, mProductID;
    public String mPName, mDate, mVendor, mDescription;
    public double mPrice, mTax, mQuantity, mTotal;
  //  public Date mDate;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mPurchaseID).append(",");
        sb.append(mCustomerID).append(",");
        sb.append(mProductID).append(",");
        sb.append("\'").append(mDate).append("\'").append(",");
        sb.append("\'").append(mPName).append("\'").append(",");
        sb.append(mQuantity).append(",");
        sb.append(mPrice).append(",");
        sb.append(mTax).append(",");
        sb.append(mTotal).append(")");
        return sb.toString();
    }
}
