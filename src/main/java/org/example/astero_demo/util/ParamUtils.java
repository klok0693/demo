package org.example.astero_demo.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class ParamUtils {

    public static String getParamInfo(final Collection<ParamInfo> paramInfos, final ShapeParam param) {
        return paramInfos.stream()
                .filter(info -> info.getParam() == param)
                .map(ParamInfo::getNewValue)
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }
}
