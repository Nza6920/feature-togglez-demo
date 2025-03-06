package com.niu.featuretogglez;

import org.springframework.stereotype.Component;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class CustomActivationStrategy implements ActivationStrategy {

    public static final String ID = "REGISTRATION_RANGE";
    public static final String PARAM_START = "start";
    public static final String PARAM_END = "end";

    @Override
    public String getId() {
        return "custom";
    }

    @Override
    public String getName() {
        return "custom strategy";
    }

    @Override
    public boolean isActive(FeatureState featureState, FeatureUser user) {
        String pattern = "yyyy-MM-dd";
        LocalDateTime start = Optional.ofNullable(featureState.getParameter(PARAM_START))
                .map(startStr -> LocalDate.parse(startStr, DateTimeFormatter.ofPattern(pattern)))
                .map(LocalDate::atStartOfDay)
                .orElseThrow(() -> new IllegalArgumentException("start date is null"));
        LocalDateTime end = Optional.ofNullable(featureState.getParameter(PARAM_END))
                .map(endStr -> LocalDate.parse(endStr, DateTimeFormatter.ofPattern(pattern)))
                .map(LocalDate::atStartOfDay)
                .orElseThrow(() -> new IllegalArgumentException("end date is null"));
        System.out.println("start:" + start + ",end:" + end);
        return false;
    }

    @Override
    public Parameter[] getParameters() {
        return new Parameter[]{
                ParameterBuilder.create("start").label("start time"),
                ParameterBuilder.create("end").label("end time")
        };
    }
}
