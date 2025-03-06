package com.niu.featuretogglez;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feature/togglz")
public class FeatureController {
    @GetMapping
    public Map<String, Boolean> featureToggle() {
        return Arrays.stream(FeatureToggle.values()).collect(Collectors.toMap(Enum::name, Feature::isActive));
    }
}
