package com.klef.soa.endsem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.endsem.entity.Tourist;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long>
{

}