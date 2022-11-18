package com.shop.campaign.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "campaign")
public class Campaign {
    @Id
    private String campaignId = UUID.randomUUID().toString();
    private String campaignCode;
    private String campaignName;
}
