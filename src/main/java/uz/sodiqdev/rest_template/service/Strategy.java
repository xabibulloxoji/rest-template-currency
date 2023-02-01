package uz.sodiqdev.rest_template.service;

import uz.sodiqdev.rest_template.entity.enam.PathType;

public interface Strategy {

    String getCurrency(String code);

    PathType getStrategyName();
}
