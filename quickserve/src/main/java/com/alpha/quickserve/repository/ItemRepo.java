package com.alpha.quickserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.quickserve.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
