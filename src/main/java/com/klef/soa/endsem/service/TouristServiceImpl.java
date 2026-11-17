
package com.klef.soa.endsem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.endsem.entity.Tourist;
import com.klef.soa.endsem.repository.TouristRepository;

@Service
public class TouristServiceImpl implements TouristService
{
    @Autowired
    private TouristRepository repository;

    @Override
    public Tourist saveTourist(Tourist tourist)
    {
        return repository.save(tourist);
    }
}
