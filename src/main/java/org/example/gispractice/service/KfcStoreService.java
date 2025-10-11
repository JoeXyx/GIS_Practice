package org.example.gispractice.service;

import org.example.gispractice.entity.KfcStore;
import org.example.gispractice.repository.KfcStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KfcStoreService {

    @Autowired
    private KfcStoreRepository repository;

    public List<KfcStore> findAll() {
        return repository.findAll();
    }

    public KfcStore save(KfcStore store) {
        return repository.save(store);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Optional<KfcStore> findById(Integer id) {
        return repository.findById(id);
    }

    public List<KfcStore> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }
}