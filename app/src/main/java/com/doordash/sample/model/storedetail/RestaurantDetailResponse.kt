package com.doordash.sample.model.storedetail

import com.google.gson.annotations.SerializedName

data class RestaurantDetailResponse(

	@field:SerializedName("max_order_size")
	val maxOrderSize: Any? = null,

	@field:SerializedName("fulfills_own_deliveries")
	val fulfillsOwnDeliveries: Boolean? = null,

	@field:SerializedName("status_type")
	val statusType: String? = null,

	@field:SerializedName("yelp_review_count")
	val yelpReviewCount: Int? = null,

	@field:SerializedName("offers_delivery")
	val offersDelivery: Boolean? = null,

	@field:SerializedName("merchant_promotions")
	val merchantPromotions: List<MerchantPromotionsItem?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("price_range")
	val priceRange: Int? = null,

	@field:SerializedName("asap_time")
	val asapTime: Any? = null,

	@field:SerializedName("provides_external_courier_tracking")
	val providesExternalCourierTracking: Boolean? = null,

	@field:SerializedName("cover_img_url")
	val coverImgUrl: String? = null,

	@field:SerializedName("delivery_fee")
	val deliveryFee: Int? = null,

	@field:SerializedName("yelp_rating")
	val yelpRating: Double? = null,

	@field:SerializedName("is_newly_added")
	val isNewlyAdded: Boolean? = null,

	@field:SerializedName("is_good_for_group_orders")
	val isGoodForGroupOrders: Boolean? = null,

	@field:SerializedName("should_show_store_logo")
	val shouldShowStoreLogo: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("menus")
	val menus: List<MenusItem?>? = null,

	@field:SerializedName("delivery_radius")
	val deliveryRadius: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("show_suggested_items")
	val showSuggestedItems: Boolean? = null,

	@field:SerializedName("service_rate")
	val serviceRate: Double? = null,

	@field:SerializedName("address")
	val address: Address? = null,

	@field:SerializedName("business")
	val business: Business? = null,

	@field:SerializedName("show_store_menu_header_photo")
	val showStoreMenuHeaderPhoto: Boolean? = null,

	@field:SerializedName("object_type")
	val objectType: String? = null,

	@field:SerializedName("composite_score")
	val compositeScore: Int? = null,

	@field:SerializedName("special_instructions_max_length")
	val specialInstructionsMaxLength: Any? = null,

	@field:SerializedName("inflation_rate")
	val inflationRate: Double? = null,

	@field:SerializedName("average_rating")
	val averageRating: Double? = null,

	@field:SerializedName("delivery_fee_details")
	val deliveryFeeDetails: DeliveryFeeDetails? = null,

	@field:SerializedName("is_only_catering")
	val isOnlyCatering: Boolean? = null,

	@field:SerializedName("max_composite_score")
	val maxCompositeScore: Int? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("offers_pickup")
	val offersPickup: Boolean? = null,

	@field:SerializedName("yelp_biz_id")
	val yelpBizId: String? = null,

	@field:SerializedName("number_of_ratings")
	val numberOfRatings: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("is_consumer_subscription_eligible")
	val isConsumerSubscriptionEligible: Boolean? = null,

	@field:SerializedName("extra_sos_delivery_fee")
	val extraSosDeliveryFee: Int? = null,

	@field:SerializedName("business_id")
	val businessId: Int? = null,

	@field:SerializedName("header_image_url")
	val headerImageUrl: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Address(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("printable_address")
	val printableAddress: String? = null,

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("subpremise")
	val subpremise: String? = null,

	@field:SerializedName("shortname")
	val shortname: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("zip_code")
	val zipCode: String? = null
)

data class MinSubtotal(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Int? = null
)

data class Business(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("business_vertical")
	val businessVertical: Any? = null
)

data class Amount(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Int? = null
)

data class DeliveryFeeDetails(

	@field:SerializedName("discount")
	val discount: Discount? = null,

	@field:SerializedName("surge_fee")
	val surgeFee: SurgeFee? = null,

	@field:SerializedName("original_fee")
	val originalFee: OriginalFee? = null,

	@field:SerializedName("final_fee")
	val finalFee: FinalFee? = null
)

data class OriginalFee(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Int? = null
)

data class MinimumSubtotalMonetaryFields(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Any? = null,

	@field:SerializedName("decimal_places")
	val decimalPlaces: Int? = null
)

data class Discount(

	@field:SerializedName("amount")
	val amount: Amount? = null,

	@field:SerializedName("min_subtotal")
	val minSubtotal: MinSubtotal? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source_type")
	val sourceType: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("discount_type")
	val discountType: String? = null
)

data class MerchantPromotionsItem(

	@field:SerializedName("delivery_fee")
	val deliveryFee: Int? = null,

	@field:SerializedName("delivery_fee_monetary_fields")
	val deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields? = null,

	@field:SerializedName("new_store_customers_only")
	val newStoreCustomersOnly: Boolean? = null,

	@field:SerializedName("minimum_subtotal_monetary_fields")
	val minimumSubtotalMonetaryFields: MinimumSubtotalMonetaryFields? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("minimum_subtotal")
	val minimumSubtotal: Any? = null
)

data class MenusItem(

	@field:SerializedName("menu_version")
	val menuVersion: Int? = null,

	@field:SerializedName("is_catering")
	val isCatering: Boolean? = null,

	@field:SerializedName("status_type")
	val statusType: String? = null,

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("is_business_enabled")
	val isBusinessEnabled: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DeliveryFeeMonetaryFields(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Int? = null,

	@field:SerializedName("decimal_places")
	val decimalPlaces: Int? = null
)

data class SurgeFee(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Int? = null
)

data class FinalFee(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Int? = null
)

data class OpenHoursItemItem(

	@field:SerializedName("hour")
	val hour: Int? = null,

	@field:SerializedName("minute")
	val minute: Int? = null
)
