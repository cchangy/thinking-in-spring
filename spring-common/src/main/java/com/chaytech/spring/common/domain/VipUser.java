package com.chaytech.spring.common.domain;

import com.chaytech.spring.common.annotation.Vip;

/**
 * @author chency
 * @email chaytech@163.com
 * @Date 2022/05/02
 */
@Vip
public class VipUser extends User {

    private String vipFlag;

    public String getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(String vipFlag) {
        this.vipFlag = vipFlag;
    }

    @Override
    public String toString() {
        return "VipUser{" +
                "vipFlag='" + vipFlag + '\'' +
                "} " + super.toString();
    }
}
