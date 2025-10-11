package org.example.gispractice.repository;

import org.example.gispractice.entity.KfcStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KfcStoreRepository extends JpaRepository<KfcStore, Integer> {
    List<KfcStore> findByNameContaining(String name);
    List<KfcStore> findByAddressContaining(String address);
}