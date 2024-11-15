package org.example.astero_demo.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.ShapeParam;

import java.util.Arrays;

@UtilityClass
public class ParamUtils {

    public static String getParamInfo(final ParamInfo[] paramInfos, final ShapeParam param) {
        return Arrays.stream(paramInfos)
                .filter(info -> info.getParam() == param)
                .map(ParamInfo::getNewValue)
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }
}
