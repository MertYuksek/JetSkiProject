package com.example.JetskiProject.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "LocationContent")
@Getter
@Setter
public class LocationContent extends Content{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}
