package com.sge.utils;

import com.sge.advice.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by wzx on 2021/12/22.
 */
public class DateFormatUtils {

    /**
     *中国标准时间转换为yyyy-MM-dd形式
     * @param cnDate
     * @return
     */
    public static String CNStringDateTrasfor(String cnDate) {
        if (StringUtils.isBlank(cnDate)) {
            return null;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf1.parse(cnDate);
            return cnDate;
        } catch (ParseException e) {
        }
        cnDate = cnDate.split(Pattern.quote("(中国标准时间)"))[0].replace("GMT+0800","GMT+08:00");
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
        String formatDate = null;
        try {
            Date date = sdf.parse(cnDate);

            formatDate = sdf1.format(date);
            return formatDate;
        } catch (ParseException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
