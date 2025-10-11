package org.example.gispractice.controller;

import org.example.gispractice.entity.KfcStore;
import org.example.gispractice.service.KfcStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class KfcStoreController {

    @Autowired
    private KfcStoreService storeService;

    // 根路径映射 - 重定向到地图页面
    @GetMapping("/")
    public String home() {
        return "redirect:/map";
    }

    // 显示地图页面
    @GetMapping("/map")
    public String showMap() {
        return "map";
    }

    // 显示列表页面（可选）
    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("stores", storeService.findAll());
        return "list";
    }
}

// REST API Controller（保持不变）
@RestController
@RequestMapping("/api/kfc")
@CrossOrigin(origins = "*")
class KfcStoreApiController {

    @Autowired
    private KfcStoreService storeService;

    @GetMapping
    public List<KfcStore> getAllStores() {
        return storeService.findAll();
    }

    @PostMapping
    public KfcStore createStore(@RequestBody KfcStore store) {
        return storeService.save(store);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KfcStore> updateStore(@PathVariable Integer id, @RequestBody KfcStore storeDetails) {
        Optional<KfcStore> optionalStore = storeService.findById(id);

        if (optionalStore.isPresent()) {
            KfcStore store = optionalStore.get();
            store.setName(storeDetails.getName());
            store.setAddress(storeDetails.getAddress());
            store.setPhone(storeDetails.getPhone());
            return ResponseEntity.ok(storeService.save(store));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Integer id) {
        if (storeService.findById(id).isPresent()) {
            storeService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}