package com.shop.campaign.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "campaignMember")
public class CampaignMember {
    @Id
    @JsonIgnore
    @Field("_id")
    private String id = UUID.randomUUID().toString();
    private String campaignId;
    private String customerId;
    private String phoneNumber;
    private String email;

}
