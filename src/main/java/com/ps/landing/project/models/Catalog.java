package com.ps.landing.project.models;

import javax.persistence.*;

@Entity
@Table(name = "catalogs")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
