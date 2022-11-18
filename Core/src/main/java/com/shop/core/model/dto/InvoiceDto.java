package com.shop.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class InvoiceDto implements BaseDto {
    @JsonIgnore
    private String invoiceId;
    private Double baseFee;
    private Double tax;
    private Double discountPercent;
    private Double discountAmount;
    private Double payAbleAmount;


}
