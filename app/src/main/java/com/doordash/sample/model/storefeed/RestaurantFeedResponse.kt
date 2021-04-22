package com.doordash.sample.model.storefeed

import com.google.gson.annotations.SerializedName

data class RestaurantFeedResponse(

	@field:SerializedName("stores")
	val stores: List<StoresItem?>? = null,

	@field:SerializedName("is_first_time_user")
	val isFirstTimeUser: Boolean? = null,

	@field:SerializedName("sort_order")
	val sortOrder: String? = null,

	@field:SerializedName("next_offset")
	val nextOffset: Int? = null,

	@field:SerializedName("show_list_as_pickup")
	val showListAsPickup: Boolean? = null,

	@field:SerializedName("num_results")
	val numResults: Int? = null
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

data class Location(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class MerchantPromotionsItem(

	@field:SerializedName("delivery_fee")
	val deliveryFee: Any? = null,

	@field:SerializedName("delivery_fee_monetary_fields")
	val deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields? = null,

	@field:SerializedName("category_new_store_customers_only")
	val categoryNewStoreCustomersOnly: Boolean? = null,

	@field:SerializedName("minimum_subtotal_monetary_fields")
	val minimumSubtotalMonetaryFields: MinimumSubtotalMonetaryFields? = null,

	@field:SerializedName("daypart_constraints")
	val daypartConstraints: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("minimum_subtotal")
	val minimumSubtotal: Any? = null
)

data class ExtraSosDeliveryFeeMonetaryFields(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Int? = null,

	@field:SerializedName("decimal_places")
	val decimalPlaces: Int? = null
)

data class DeliveryFeeMonetaryFields(

	@field:SerializedName("display_string")
	val displayString: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("unit_amount")
	val unitAmount: Any? = null,

	@field:SerializedName("decimal_places")
	val decimalPlaces: Int? = null
)

data class MenusItem(

	@field:SerializedName("popular_items")
	val popularItems: List<PopularItemsItem?>? = null,

	@field:SerializedName("is_catering")
	val isCatering: Boolean? = null,

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class PopularItemsItem(

	@field:SerializedName("img_url")
	val imgUrl: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class StoresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("next_open_time")
	val nextOpenTime: String? = null,

	@field:SerializedName("merchant_promotions")
	val merchantPromotions: List<MerchantPromotionsItem?>? = null,

	@field:SerializedName("promotion_delivery_fee")
	val promotionDeliveryFee: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("price_range")
	val priceRange: Int? = null,

	@field:SerializedName("cover_img_url")
	val coverImgUrl: String? = null,

	@field:SerializedName("delivery_fee")
	val deliveryFee: Int? = null,

	@field:SerializedName("delivery_fee_monetary_fields")
	val deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields? = null,

	@field:SerializedName("extra_sos_delivery_fee_monetary_fields")
	val extraSosDeliveryFeeMonetaryFields: ExtraSosDeliveryFeeMonetaryFields? = null,

	@field:SerializedName("num_ratings")
	val numRatings: Int? = null,

	@field:SerializedName("is_newly_added")
	val isNewlyAdded: Boolean? = null,

	@field:SerializedName("distance_from_consumer")
	val distanceFromConsumer: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("menus")
	val menus: List<MenusItem?>? = null,

	@field:SerializedName("service_rate")
	val serviceRate: Any? = null,

	@field:SerializedName("display_delivery_fee")
	val displayDeliveryFee: String? = null,

	@field:SerializedName("average_rating")
	val averageRating: Double? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("next_close_time")
	val nextCloseTime: String? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("is_consumer_subscription_eligible")
	val isConsumerSubscriptionEligible: Boolean? = null,

	@field:SerializedName("header_img_url")
	val headerImgUrl: String? = null,

	@field:SerializedName("business_id")
	val businessId: Int? = null,

	@field:SerializedName("extra_sos_delivery_fee")
	val extraSosDeliveryFee: Int? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Status(

	@field:SerializedName("asap_minutes_range")
	val asapMinutesRange: List<Int?>? = null,

	@field:SerializedName("unavailable_reason")
	val unavailableReason: Any? = null,

	@field:SerializedName("scheduled_available")
	val scheduledAvailable: Boolean? = null,

	@field:SerializedName("asap_available")
	val asapAvailable: Boolean? = null,

	@field:SerializedName("pickup_available")
	val pickupAvailable: Boolean? = null
)
