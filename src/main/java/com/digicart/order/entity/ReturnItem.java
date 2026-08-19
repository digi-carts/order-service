package com.digicart.order.entity;

import jakarta.persistence.*;

/**
 * JPA entity mapped in this service schema (Return Item).
 */
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

    /**
     * Returns id.
     * @return the string
     */
    public String getId() { return id; }
    /**
     * Sets id.
     *
     * @param id resource identifier
     */
    public void setId(String id) { this.id = id; }
    /**
     * Returns return request.
     * @return the return
     */
    public Return getReturnRequest() { return returnRequest; }
    /**
     * Sets return request.
     *
     * @param returnRequest request payload
     */
    public void setReturnRequest(Return returnRequest) { this.returnRequest = returnRequest; }
    /**
     * Returns order item id.
     * @return the string
     */
    public String getOrderItemId() { return orderItemId; }
    /**
     * Sets order item id.
     *
     * @param orderItemId order item id
     */
    public void setOrderItemId(String orderItemId) { this.orderItemId = orderItemId; }
    /**
     * Returns product id.
     * @return the string
     */
    public String getProductId() { return productId; }
    /**
     * Sets product id.
     *
     * @param productId product id
     */
    public void setProductId(String productId) { this.productId = productId; }
    /**
     * Returns product name.
     * @return the string
     */
    public String getProductName() { return productName; }
    /**
     * Sets product name.
     *
     * @param productName product name
     */
    public void setProductName(String productName) { this.productName = productName; }
    /**
     * Returns qty.
     * @return the integer
     */
    public Integer getQty() { return qty; }
    /**
     * Sets qty.
     *
     * @param qty qty
     */
    public void setQty(Integer qty) { this.qty = qty; }
    /**
     * Returns price at order.
     * @return the double
     */
    public Double getPriceAtOrder() { return priceAtOrder; }
    /**
     * Sets price at order.
     *
     * @param priceAtOrder price at order
     */
    public void setPriceAtOrder(Double priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
