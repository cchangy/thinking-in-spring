package com.chency.spring.common.domain;

import com.chency.spring.common.annotation.Vip;
import lombok.Data;

/**
 * @author chency
 * @Date 2022/05/02
 */
@Vip
@Data
public class VipUser extends User {

    private String vipFlag;

    @Override
    public String toString() {
        return "VipUser{" +
                "vipFlag='" + vipFlag + '\'' +
                "} " + super.toString();
    }
}
