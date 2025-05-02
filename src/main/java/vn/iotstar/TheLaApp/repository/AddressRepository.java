package vn.iotstar.TheLaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.entity.Address;
import vn.iotstar.TheLaApp.entity.CartItem;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.Review;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
