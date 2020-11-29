package com.project.kuzmichev.model.repository;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.ad.AdCategory;
import com.project.kuzmichev.model.domain.ad.AdType;
import org.springframework.data.jpa.domain.Specification;

public class FilmFilterSpecification {

    public static Specification<Ad> byAdCategory(AdCategory adCategory) {
        return (Specification<Ad>) (root, criteriaQuery, criteriaBuilder) -> {
            if(adCategory != null) {
                return criteriaBuilder.equal(root.get("adCategory"), adCategory);
            }
            return null;
        };
    }

    public static Specification<Ad> byAdType(AdType adType) {
        return (Specification<Ad>) (root, criteriaQuery, criteriaBuilder) -> {
            if(adType != null) {
                return criteriaBuilder.equal(root.get("adType"), adType);
            }
            return null;
        };
    }

    public static Specification<Ad> greaterThanMinCost(float minCost) {
        return (Specification<Ad>) (root, criteriaQuery, criteriaBuilder) -> {
            if(minCost != 0) {
                return criteriaBuilder.greaterThan(root.get("cost"), minCost);
            }
            return null;
        };
    }

    public static Specification<Ad> lessThanMaxCost(float maxCost) {
        return (Specification<Ad>) (root, criteriaQuery, criteriaBuilder) -> {
            if(maxCost != 0) {
                return criteriaBuilder.lessThan(root.get("cost"), maxCost);
            }
            return null;
        };
    }
}
