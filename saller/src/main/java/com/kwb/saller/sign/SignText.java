package com.kwb.saller.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kwb.util.common.JsonUtil;

/**
 * 字典排序
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {
    default String toText(){
        return JsonUtil.toJSON(this);
    }
}
