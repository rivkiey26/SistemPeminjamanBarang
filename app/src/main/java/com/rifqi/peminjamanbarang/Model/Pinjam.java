package com.rifqi.peminjamanbarang.Model;

/**
 * Created by USER on 28/12/2017.
 */

public class Pinjam {
    private String ProductId,ProductName,Quantity;

    public Pinjam(String productId, String productName, String quantity) {
        ProductId = productId;
        ProductName = productName;
        Quantity = quantity;
    }

    public Pinjam() {
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
