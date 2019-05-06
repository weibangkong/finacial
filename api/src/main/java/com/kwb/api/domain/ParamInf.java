package com.kwb.api.domain;

import java.math.BigDecimal;
import java.util.List;

public interface ParamInf {

    public List<String> getIdList();

    public BigDecimal getMinRewardRate();

    public BigDecimal getMaxRewardRate();

    public List<String> getStatusList();
}
