package com.digicart.order.entity;

import jakarta.persistence.*;

/**
 * JPA entity mapped in this service schema (Order Item).
 */
@Entity
@Table(name = "order_items", schema = "order_svc")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

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
     * Returns order.
     * @return the order
     */
    public Order getOrder() { return order; }
    /**
     * Sets order.
     *
     * @param order order
     */
    public void setOrder(Order order) { this.order = order; }
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
