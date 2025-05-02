package vn.iotstar.TheLaApp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.iotstar.TheLaApp.dto.AddressDto;
import vn.iotstar.TheLaApp.dto.CartDto;
import vn.iotstar.TheLaApp.dto.CartItemDto;
import vn.iotstar.TheLaApp.dto.CategoryDto;
import vn.iotstar.TheLaApp.dto.OrderDetailDto;
import vn.iotstar.TheLaApp.dto.OrderDto;
import vn.iotstar.TheLaApp.dto.PaymentMethodDto;
import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.dto.ProductImageDto;
import vn.iotstar.TheLaApp.dto.ProductSizeDto;
import vn.iotstar.TheLaApp.dto.PromotionDto;
import vn.iotstar.TheLaApp.dto.ReviewDto;
import vn.iotstar.TheLaApp.dto.ReviewImageDto;
import vn.iotstar.TheLaApp.dto.SizeDto;
import vn.iotstar.TheLaApp.dto.UserDto;
import vn.iotstar.TheLaApp.entity.Address;
import vn.iotstar.TheLaApp.entity.Cart;
import vn.iotstar.TheLaApp.entity.CartItem;
import vn.iotstar.TheLaApp.entity.Category;
import vn.iotstar.TheLaApp.entity.Order;
import vn.iotstar.TheLaApp.entity.OrderDetail;
import vn.iotstar.TheLaApp.entity.PaymentMethod;
import vn.iotstar.TheLaApp.entity.Product;
import vn.iotstar.TheLaApp.entity.ProductImage;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.Promotion;
import vn.iotstar.TheLaApp.entity.Review;
import vn.iotstar.TheLaApp.entity.ReviewImage;
import vn.iotstar.TheLaApp.entity.Size;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.AddressRepository;
import vn.iotstar.TheLaApp.repository.CartItemRepository;
import vn.iotstar.TheLaApp.repository.OrderDetailRepository;

@Component
public class ConvertToDto {
	@Autowired
	private OrderDetailRepository orderDetailrepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public CategoryDto convertToCategoryDTO(Category category) {
		if (category == null) {
	        return null;
	    }
		
	    return new CategoryDto(
	        category.getCategoryId(),
	        category.getName(),
	        category.getImage()
	    );
	}
	
	public PaymentMethodDto convertToPaymentMethodDTO(PaymentMethod item) {
		if (item == null) {
	        return null;
	    }
		
	    return new PaymentMethodDto(
	        item.getPaymentMethodId(),
	        item.getName(),
	        item.getImage()
	    );
	}
	
	public OrderDetailDto convertToOrderDetailDTO(OrderDetail item) {
		if (item == null) {
	        return null;
	    }
		
	    return new OrderDetailDto(
	        item.getOrderDetailId(),
	        item.getQuantity(),
	        item.getPrice(),
	        convertToProductSizeDTO(item.getProductSize()),
	        convertToReviewDTO(item.getReview())
	    );
	}
	
	public ProductDto convertToProductDTO(Product product) {
		if (product == null) {
	        return null;
	    }
		
	    return new ProductDto(
	        product.getProductId(),
	        product.getName(),
	        product.getDescription(),
	        product.getStatus(),
	        getProductMainImage(product.getProductImages())
	    );
	}
	
	private String getProductMainImage(List<ProductImage> images) {
		if (images.isEmpty()) {
	        return null;
	    }
		
		for (ProductImage x:images) {
			if (x.getIsMain()) {
				return x.getImage();
			}
		}
		return null;
	}
	
	public PromotionDto convertToPromotionDTO(Promotion promotion) {
		if (promotion == null) {
	        return null;
	    }
		
	    return new PromotionDto(
	    	promotion.getPromotionId(),
	    	promotion.getName(),
	    	promotion.getImage(),
	    	promotion.getDescription(),
	        promotion.getDiscountPercentage(),
	        promotion.getMinimumOrderValue(),
	        promotion.getStartDate(),
	        promotion.getEndDate(),
	        promotion.getQuantity(),
	        promotion.getQuantityUsed()
	    );
	}

	public ReviewDto convertToReviewDTO(Review review) {
		if (review == null) {
	        return null;
	    }
		
		return new ReviewDto(
	    	review.getReviewId(),
	    	review.getRating(),
	    	review.getContent(),
	    	review.getReviewDate(),
	    	review.getIsModify(),
	    	convertToUserDTO(review.getUser()),
	    	convertToListReviewImageDto(review.getReviewImages())
	    );
	}
	
	public ProductImageDto convertToProductImageDTO(ProductImage item) {
		if (item == null) {
	        return null;
	    }
		
		return new ProductImageDto(
	    	item.getImageId(),
	    	item.getImage(),
	   		item.getIsMain()
	    );
	}
	
	public ReviewImageDto convertToReviewImageDTO(ReviewImage reviewImage) {
		if (reviewImage == null) {
	        return null;
	    }
		
		return new ReviewImageDto(
	    	reviewImage.getImageId(),
	    	reviewImage.getImage()
	    );
	}
	
	public CartDto convertToCartDTO(Cart cart) {
		if (cart == null) {
	        return null;
	    }
		List<CartItem> list = cartItemRepository.getCartItemsByCartId(cart.getCartId());
		cart.setCartItems(list);
		return new CartDto (
			cart.getCartId(),
			convertToListCartItemDto(cart.getCartItems())
	    );
	}

	public UserDto convertToUserDTO(User user) {
		if (user == null) {
	        return null;
	    }
		
		return new UserDto(
	    	user.getUserId(),
	    	user.getName(),
	    	user.getEmail(),
	    	user.getPhone(),
	    	user.getRole(),
	    	user.getImage(),
	    	user.getIsActive(),
	    	convertToCartDTO(user.getCart()),
	    	convertToAddressDto(user.getAddress())
	    );
	}
	
	public SizeDto convertToSizeDTO(Size item) {
		if (item == null) {
	        return null;
	    }
		
		return new SizeDto(
	    	item.getSizeId(),
	    	item.getName(),
	    	item.getDescription()
	    );
	}
	
	public ProductSizeDto convertToProductSizeDTO(ProductSize item) {
		if (item == null) {
	        return null;
	    }
		
		return new ProductSizeDto(
	    	item.getProductSizeId(),
	    	item.getPrice(),
	    	convertToSizeDTO(item.getSize()),
	    	convertToProductDTO(item.getProduct())
	    );
	}
	
	public OrderDto convertToOrderDTO(Order item) {
		if (item == null) {
	        return null;
	    }
		
		List<OrderDetail> orderDetails = orderDetailrepository.getOrderDetails(item.getOrderId());
		item.setOrderDetails(orderDetails);
		
	    return new OrderDto(
	    	item.getOrderId(),
	    	item.getTotalPrice(),
	    	item.getNote(),
	    	item.getCreateDate(),
	    	item.getDeliveryDate(),
	    	item.getDeliveryAddress(),
	    	item.getDeliveryPhone(),
	    	item.getStatus(),
	    	convertToPromotionDTO(item.getPromotion()),
	    	convertToListOrderDetailDto(item.getOrderDetails()),
	    	item.getName(),
	    	convertToPaymentMethodDTO(item.getPaymentMethod())
	    );
	}
	
	public CartItemDto convertToCartItemDto(CartItem item) {
		if (item == null) {
	        return null;
	    }
		
		return new CartItemDto(
	    	item.getCartItemId(),
	    	item.getQuantity(),
	    	item.getAddDate(),
	    	convertToProductSizeDTO(item.getProductSize())
	    );
	}
	
	public AddressDto convertToAddressDto(Address item) {
		if (item == null) {
	        return null;
	    }
		
		return new AddressDto (
				item.getAddressId(),
			    item.getCity(),
			    item.getDistrict(),
			    item.getCommune(),
			    item.getDetail()
	    );
	}

	public List<CartItemDto> convertToListCartItemDto(List<CartItem> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<CartItemDto> dtos = new ArrayList<>();

	    for (CartItem item : list) {
	        dtos.add(convertToCartItemDto(item));
	    }

	    return dtos;
	}
	
	public List<AddressDto> convertToListAddressDto(List<Address> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<AddressDto> dtos = new ArrayList<>();

	    for (Address item : list) {
	        dtos.add(convertToAddressDto(item));
	    }

	    return dtos;
	}
	
	public List<PaymentMethodDto> convertToListPaymentMethodDto(List<PaymentMethod> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<PaymentMethodDto> dtos = new ArrayList<>();

	    for (PaymentMethod item : list) {
	        dtos.add(convertToPaymentMethodDTO(item));
	    }

	    return dtos;
	}
	
	public List<OrderDetailDto> convertToListOrderDetailDto(List<OrderDetail> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<OrderDetailDto> dtos = new ArrayList<>();

	    for (OrderDetail item : list) {
	        dtos.add(convertToOrderDetailDTO(item));
	    }

	    return dtos;
	}
	
	public List<ProductSizeDto> convertToListProductSizeDto(List<ProductSize> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<ProductSizeDto> dtos = new ArrayList<>();

	    for (ProductSize item : list) {
	        dtos.add(convertToProductSizeDTO(item));
	    }

	    return dtos;
	}
	
	public List<ReviewDto> convertToListReviewDto(List<Review> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<ReviewDto> dtos = new ArrayList<>();
		
		for (Review item:list) {
			dtos.add(convertToReviewDTO(item));
		}
		
		return dtos;
	}
	
	public List<ReviewImageDto> convertToListReviewImageDto(List<ReviewImage> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<ReviewImageDto> listDto = new ArrayList<>();
		
		for (ReviewImage item : list) {
			listDto.add(convertToReviewImageDTO(item));
		}
		
	    return listDto;
	}

	public List<ProductImageDto> convertToListProductImageDto(List<ProductImage> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<ProductImageDto> listDto = new ArrayList<>();
		
		for (ProductImage item : list) {
			listDto.add(convertToProductImageDTO(item));
		}
		
	    return listDto;
	}
	
	public List<CategoryDto> convertToListCategoryDto(List<Category> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<CategoryDto> listDto = new ArrayList<>();
		
		for (Category item : list) {
			listDto.add(convertToCategoryDTO(item));
		}
		
	    return listDto;
	}
	
	public List<ProductDto> convertToListProductDto(List<Product> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<ProductDto> listDto = new ArrayList<>();
		
		for (Product item : list) {
			listDto.add(convertToProductDTO(item));
		}
		
	    return listDto;
	}
	
	public List<PromotionDto> convertToListPromotionDto(List<Promotion> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<PromotionDto> listDto = new ArrayList<>();
		
		for (Promotion item : list) {
			listDto.add(convertToPromotionDTO(item));
		}
		
	    return listDto;
	}
	
	public List<OrderDto> convertToListOrderDto(List<Order> list) {
		if (list == null || list.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		List<OrderDto> listDto = new ArrayList<>();
		
		for (Order item : list) {
			listDto.add(convertToOrderDTO(item));
		}
		
	    return listDto;
	}
}
