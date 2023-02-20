package com.example.runway.convertor;

import com.example.runway.domain.Store;
import com.example.runway.domain.StoreReview;
import com.example.runway.dto.map.MapRes;
import com.example.runway.dto.store.StoreRes;
import com.example.runway.repository.OwnerBoardRepository;
import com.example.runway.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreConvertor {
    public static MapRes.MapMarkerList StoreMapBuilder(Store value) {
        return MapRes.MapMarkerList.builder().storeId(value.getId()).storeName(value.getName()).latitude(value.getLatitude()).longitude(value.getLongitude()).build();
    }

    public static StoreRes.StoreInfo getStoreDetail(StoreRepository.StoreInfo storeResult, boolean checkBookMark, List<String> imgList) {
        return StoreRes.StoreInfo.builder()
                .storeId(storeResult.getStoreId())
                .imgUrlList(imgList)
                .category(Stream.of(storeResult.getCategory().split(",")).collect(Collectors.toList()))
                .storeName(storeResult.getStoreName())
                .address(storeResult.getAddress())
                .storeTime(storeResult.getStoreTime())
                .storePhone(storeResult.getStorePhone())
                .instagram(storeResult.getInstagram())
                .webSite(storeResult.getWebsite())
                .bookmark(checkBookMark).build();
    }

    public static StoreRes.StoreReview StoreReviewBuilder(StoreReview review) {
        return StoreRes.StoreReview.builder().reviewId(review.getId()).imgUrl(review.getImgUrl()).build();
    }

    public static MapRes.StoreInfo StoreInfo(StoreRepository.StoreInfoList storeResult, List<String> storeCategory) {
        return MapRes.StoreInfo.builder()
                .storeId(storeResult.getStoreId())
                .storeImg(storeResult.getStoreImg())
                .storeName(storeResult.getStoreName())
                .category(storeCategory).build();
    }

    public static MapRes.MapMarkerList PositionBuilder(StoreRepository.StoreInfoList storeResult) {
        return MapRes.MapMarkerList.builder()
                .storeId(storeResult.getStoreId())
                .storeName(storeResult.getStoreName())
                .address(storeResult.getAddress())
                .latitude(storeResult.getLatitude())
                .longitude(storeResult.getLongitude()).build();
    }

    public static StoreRes.StoreBoard StoreBoard(OwnerBoardRepository.StoreBoard result, Long userId, List<String> categoryList) {
        return StoreRes.StoreBoard.builder()
                .myBoard(result.getUserId().equals(userId))
                .bookmark(result.getBookmark())
                .imgUrl(categoryList)
                .boardId(result.getBoardId())
                .title(result.getTitle())
                .day(result.getDay())
                .content(result.getContent())
                .storeId(result.getStoreId())
                .storeName(result.getStoreName())
                .regionInfo(result.getRegionInfo())
                .build();
    }
}
