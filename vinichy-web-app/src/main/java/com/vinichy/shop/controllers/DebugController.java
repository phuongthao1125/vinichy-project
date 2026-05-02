package com.vinichy.shop.controllers;

import com.vinichy.shop.models.SanPham;
import com.vinichy.shop.models.SanPhamCT;
import com.vinichy.shop.models.HinhAnhSP_CT;
import com.vinichy.shop.repositories.SanPhamRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RestController
public class DebugController {
    @Autowired
    private SanPhamRepository repo;

    @GetMapping("/debug/sp/{id}")
    @Transactional
    public Map<String, Object> debugSp(@PathVariable Long id) {
        SanPham sp = repo.findById(id).orElse(null);
        if (sp == null) return Map.of("error", "not found");
        
        List<Map<String, Object>> variants = new ArrayList<>();
        if (sp.getSanPhamCTList() != null) {
            for (SanPhamCT ct : sp.getSanPhamCTList()) {
                Map<String, Object> vMap = new HashMap<>();
                vMap.put("id", ct.getId());
                vMap.put("hinhAnhSize", ct.getHinhAnhList() != null ? ct.getHinhAnhList().size() : -1);
                
                List<String> links = new ArrayList<>();
                if (ct.getHinhAnhList() != null) {
                    for (HinhAnhSP_CT ha : ct.getHinhAnhList()) {
                        links.add(ha.getLinkHinhAnh());
                    }
                }
                vMap.put("links", links);
                variants.add(vMap);
            }
        }

        return Map.of(
            "hinhAnhDaiDien", sp.getHinhAnhDaiDien(),
            "ctListSize", sp.getSanPhamCTList() == null ? -1 : sp.getSanPhamCTList().size(),
            "variants", variants
        );
    }
}
