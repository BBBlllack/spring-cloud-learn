package com.bbblllack.config;

import com.bbblllack.model.user.UserStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MyEnumConverter implements Converter<String, UserStatus> {
    @Override
    public UserStatus convert(String source) {
        if ("ACTIVE".equalsIgnoreCase(source)){
            return UserStatus.ACTIVE;
        }else if ("INACTIVE".equalsIgnoreCase(source)){
            return UserStatus.INACTIVE;
        }else{
            return UserStatus.ILLEGAL;
        }
    }
}
