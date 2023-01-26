
package uz.sodiqdev.rest_template.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "currencies")
public class Currency {

    @Expose
    @Id
    @JsonProperty("id")
    private Long id;

    @Column(name = "Ccy")
    @JsonProperty("Ccy")
    private String ccy;

    @Column(name = "CcyNm_EN")
    @JsonProperty("CcyNm_EN")
    private String ccyNmEN;

    @Column(name = "CcyNm_RU")
    @JsonProperty("CcyNm_RU")
    private String ccyNmRU;

    @Column(name = "CcyNm_UZ")
    @JsonProperty("CcyNm_UZ")
    private String ccyNmUZ;

    @Column(name = "CcyNm_UZC")
    @JsonProperty("CcyNm_UZC")
    private String ccyNmUZC;

    @Column(name = "Code")
    @JsonProperty("Code")
    private String code;

    @Column(name = "Date")
    @JsonProperty("Date")
    private String date;

    @Column(name = "Diff")
    @JsonProperty("Diff")
    private String diff;

    @Column(name = "Nominal")
    @JsonProperty("Nominal")
    private String nominal;

    @Column(name = "Rate")
    @JsonProperty("Rate")
    private String rate;



}
