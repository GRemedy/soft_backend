package com.bistu.dis;

import com.bistu.Enum.DamageLevel;
import com.bistu.Enum.ProductStatus;
import com.bistu.Enum.PurchaseMethod;
import com.bistu.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisProduct {
    private int id;
    private String name;
    private String category;
    private String size;
    private String description;
    private double price;
    private String image;
    private boolean negotiable;
    private PurchaseMethod purchaseMethod;
    private int quantity;
    private int salesVolume;
    private List<Comment> comments;
    private DamageLevel damageLevel;
    private ProductStatus status;
    private double rating;

    public List<Comment> getComments() {
        if (comments == null){
            comments = new ArrayList<>();
        }
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    private List<LocalDateTime> paymentTimes;
    public List<LocalDateTime> getPaymentTimes() {
        if (paymentTimes == null) {
            paymentTimes = new ArrayList<>();
        }
        return paymentTimes;
    }

    public void setPaymentTimes(List<LocalDateTime> paymentTimes) {
        this.paymentTimes = paymentTimes;
    }

}
