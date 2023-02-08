package uz.sodiqdev.rest_template.service;

import uz.sodiqdev.rest_template.entity.enam.PathType;

import java.util.Map;

public interface Strategy {

    Map<String, String> getCurrency(String code);

    PathType getStrategyName();
}
