package com.shop.campaign.repository;

import com.shop.campaign.entity.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign,String> {
}
