package com.project.kuzmichev.utils;


import com.project.kuzmichev.model.domain.ad.AdCategory;
import com.project.kuzmichev.model.domain.ad.AdType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AdFilter {
    private AdCategory adCategory;
    private AdType adType;
    private float minCost;
    private float maxCost;

    @Override
    public String toString() {
        return "AdFilter{" +
                "adCategory=" + adCategory +
                ", adType=" + adType +
                ", minCost=" + minCost +
                ", maxCost=" + maxCost +
                '}';
    }

    public boolean isEmpty(){
        return adCategory==null & adType==null & minCost== 0.0 & maxCost==0.0;
    }
}
