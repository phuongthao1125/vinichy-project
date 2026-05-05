package com.vinichy.shop.repositories;

import com.vinichy.shop.models.HinhAnhSP_CT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhAnhSP_CTRepository extends JpaRepository<HinhAnhSP_CT, Long> {
}
