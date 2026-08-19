package com.digicart.order.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "return_items", schema = "order_svc")
public class ReturnItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_id", nullable = false)
    private Return returnRequest;

    @Column(name = "order_item_id", nullable = false)
    private String orderItemId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "price_at_order", nullable = false)
    private Double priceAtOrder;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Return getReturnRequest() { return returnRequest; }
    public void setReturnRequest(Return returnRequest) { this.returnRequest = returnRequest; }
    public String getOrderItemId() { return orderItemId; }
    public void setOrderItemId(String orderItemId) { this.orderItemId = orderItemId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }
    public Double getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(Double priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
