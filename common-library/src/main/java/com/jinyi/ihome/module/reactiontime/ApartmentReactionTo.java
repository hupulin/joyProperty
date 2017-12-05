package com.jinyi.ihome.module.reactiontime;

import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.apartment.ApartmentPlaceTo;

/**
 * Created by fz on 2016/5/24.
 */
public class ApartmentReactionTo extends ApartmentInfoTo {

    private String pinyin;
    private String shortpy;
    private ApartmentPlaceTo apartmentPlaceTo;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getShortpy() {
        return shortpy;
    }

    public void setShortpy(String shortpy) {
        this.shortpy = shortpy;
    }

    public ApartmentPlaceTo getApartmentPlaceTo() {
        return apartmentPlaceTo;
    }

    public void setApartmentPlaceTo(ApartmentPlaceTo apartmentPlaceTo) {
        this.apartmentPlaceTo = apartmentPlaceTo;
    }
}
