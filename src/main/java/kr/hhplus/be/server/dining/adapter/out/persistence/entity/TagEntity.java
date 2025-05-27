package kr.hhplus.be.server.dining.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected TagEntity() {}
} 
