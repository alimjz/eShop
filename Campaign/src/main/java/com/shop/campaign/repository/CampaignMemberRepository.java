package com.shop.campaign.repository;

import com.shop.campaign.entity.CampaignMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignMemberRepository extends MongoRepository<CampaignMember,String> {
}
