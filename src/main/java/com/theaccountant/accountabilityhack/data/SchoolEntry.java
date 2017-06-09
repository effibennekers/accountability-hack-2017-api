package com.theaccountant.accountabilityhack.data;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@Table(name = "schools")
public class SchoolEntry {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String brin;
    private Integer bevoegdGezag;
    private String name;
    private Address address;
    private Coordinate geo;

    private Integer totaalAantalLeerlingen;

    private BigDecimal fteDirectie;
    private BigDecimal fteLeerkrachten;
    private BigDecimal fteInOpleiding;
    private BigDecimal fteOndersteunend;
    private BigDecimal fteOnbekend;

    private BigDecimal bekostigingPersoneel;
    private BigDecimal bekostigingDirectie;
    private BigDecimal bekostigingOverig;
}
