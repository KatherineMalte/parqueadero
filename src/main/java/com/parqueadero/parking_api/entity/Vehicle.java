package com.parqueadero.parking_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parqueadero.parking_api.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true, length = 10)
	    private String plate;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private VehicleType type;

	    @Column(nullable = false)
	    private LocalDateTime createdAt;
	    @JsonIgnore
	    @OneToMany(mappedBy = "vehicle")
	    private List<ParkingRecord> parkingRecords;

	    @PrePersist
	    public void prePersist() {
	        this.createdAt = LocalDateTime.now();
	    }
}
