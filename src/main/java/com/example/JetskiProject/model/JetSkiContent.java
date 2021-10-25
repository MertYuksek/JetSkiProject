package com.example.JetskiProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "JetSkiContent")
@Getter
@Setter
public class JetSkiContent extends Content{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jetSki_id")
    private JetSki jetSki;
}
