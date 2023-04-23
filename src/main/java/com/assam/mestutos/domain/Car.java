package com.assam.mestutos.domain;

import com.assam.mestutos.domain.enumeration.ModelEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private ModelEnum model;
}
