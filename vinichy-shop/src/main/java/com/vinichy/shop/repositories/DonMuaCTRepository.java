package com.vinichy.shop.repositories;

import com.vinichy.shop.models.DonMuaCT;
import com.vinichy.shop.models.DonMuaCTId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DonMuaCTRepository extends JpaRepository<DonMuaCT, DonMuaCTId> {

    // Eager load sanPhamCT → sanPham, mauSac, hinhAnhList để tránh LazyInitializationException
    @Query("SELECT d FROM DonMuaCT d " +
           "JOIN FETCH d.sanPhamCT spct " +
           "JOIN FETCH spct.sanPham sp " +
           "LEFT JOIN FETCH spct.mauSac " +
           "LEFT JOIN FETCH spct.hinhAnhList " +
           "WHERE d.donMua.id = :donMuaId")
    List<DonMuaCT> findByDonMuaId(@Param("donMuaId") Long donMuaId);
}
