package com.theaccountant.accountabilityhack.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@Table(name = "schools")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class SchoolEntry {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String brin;
    private Integer bevoegdGezag;
    private String name;

    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Coordinate geo;

    private Integer totaalAantalLeerlingen;

    private String denomination;
    private String soortPo;
    private BigDecimal cetAverage;
    private BigDecimal iepAverage;

    private BigDecimal fteDirectie;
    private BigDecimal fteLeerkrachten;
    private BigDecimal fteInOpleiding;
    private BigDecimal fteOndersteunend;
    private BigDecimal fteOnbekend;

    private BigDecimal bekostigingPersoneel;
    private BigDecimal bekostigingDirectie;
    private BigDecimal bekostigingPersoneelOverig;
}
