package com.project.kuzmichev.model.domain.user;

import com.project.kuzmichev.model.domain.ad.AdCategory;
import com.project.kuzmichev.model.domain.ad.AdType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private AdCategory adCategory;
    private AdType adType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSend;

    @Override
    public String toString() {
        return "Subscriptions{" +
                "id=" + id +
                ", adCategory=" + adCategory +
                ", adType=" + adType +
                ", lastSend=" + lastSend +
                '}';
    }
}
