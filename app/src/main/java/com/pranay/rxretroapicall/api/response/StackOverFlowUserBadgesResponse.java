package com.pranay.rxretroapicall.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pranay.
 */

public class StackOverFlowUserBadgesResponse {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private Integer quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private Integer quotaRemaining;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(Integer quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    public class User {

        @SerializedName("reputation")
        @Expose
        private Integer reputation;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("accept_rate")
        @Expose
        private Integer acceptRate;
        @SerializedName("profile_image")
        @Expose
        private String profileImage;
        @SerializedName("display_name")
        @Expose
        private String displayName;
        @SerializedName("link")
        @Expose
        private String link;

        public Integer getReputation() {
            return reputation;
        }

        public void setReputation(Integer reputation) {
            this.reputation = reputation;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Integer getAcceptRate() {
            return acceptRate;
        }

        public void setAcceptRate(Integer acceptRate) {
            this.acceptRate = acceptRate;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

    }

    public class Item {

        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("badge_type")
        @Expose
        private String badgeType;
        @SerializedName("award_count")
        @Expose
        private Integer awardCount;
        @SerializedName("rank")
        @Expose
        private String rank;
        @SerializedName("badge_id")
        @Expose
        private Integer badgeId;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("name")
        @Expose
        private String name;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getBadgeType() {
            return badgeType;
        }

        public void setBadgeType(String badgeType) {
            this.badgeType = badgeType;
        }

        public Integer getAwardCount() {
            return awardCount;
        }

        public void setAwardCount(Integer awardCount) {
            this.awardCount = awardCount;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public Integer getBadgeId() {
            return badgeId;
        }

        public void setBadgeId(Integer badgeId) {
            this.badgeId = badgeId;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
