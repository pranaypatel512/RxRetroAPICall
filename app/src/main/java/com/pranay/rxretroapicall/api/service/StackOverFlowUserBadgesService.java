package com.pranay.rxretroapicall.api.service;

import com.pranay.rxretroapicall.api.response.StackOverFlowUserBadgesResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Pranay.
 */

public interface StackOverFlowUserBadgesService {
    //get User Badges
    @GET("users/{userId}/badges?page=1&pagesize=80&order=desc&sort=type&site=stackoverflow")
    Observable<StackOverFlowUserBadgesResponse> getBadges(@Path("userId") String userId);
}
