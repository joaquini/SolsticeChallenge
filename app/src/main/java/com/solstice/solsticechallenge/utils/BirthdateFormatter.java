package com.solstice.solsticechallenge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class BirthdateFormatter {

    private static final String ORIGINAL_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DESIRED_DATE_PATTERN = "MMMM d, yyyy";

    @Inject
    public BirthdateFormatter() {
    }

    public String getFormattedBirthdate(String birthDate) {
        try {
            Date date = new SimpleDateFormat(ORIGINAL_DATE_PATTERN).parse(birthDate);
            return new SimpleDateFormat(DESIRED_DATE_PATTERN).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
