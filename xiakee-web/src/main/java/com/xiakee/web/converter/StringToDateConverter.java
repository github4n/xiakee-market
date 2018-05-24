package com.xiakee.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: lvyanmeng
 * Date: 13-11-19
 * Time: 下午3:22
 *  日期转换器
 */
public class StringToDateConverter implements Converter<String, Date> {

    private String dateFormatPattern = "yyyy-MM-dd";

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    @Override
    public Date convert(String source) {
//        System.out.println("==================================================");
        if(StringUtils.isEmpty(source)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern);
        Date resultDate = null;
        try {
            resultDate = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }
}