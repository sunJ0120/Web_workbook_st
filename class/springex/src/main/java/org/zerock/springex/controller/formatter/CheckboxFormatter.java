package org.zerock.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter<Boolean> {

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if(text == null){
            return false;
        }
        return text.equals("on"); //이렇게 해서 text가 on이랑 같은지 아닌지를 볼 수 있다.
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }
}
