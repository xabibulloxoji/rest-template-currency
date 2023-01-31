package uz.sodiqdev.rest_template.service;

import uz.sodiqdev.rest_template.entity.enam.Url;

public interface Strategy {

    String getCurrency(String code);

    Url getStrategyName();
}
