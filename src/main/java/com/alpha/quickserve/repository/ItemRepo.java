package com.alpha.quickserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Item;
@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

}
