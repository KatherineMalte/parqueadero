package com.parqueadero.parking_api.entity;
import com.parqueadero.parking_api.enums.ParkingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingRecord {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "vehicle_id", nullable = false)
	    private Vehicle vehicle;

	    @Column(nullable = false)
	    private LocalDateTime entryTime;

	    private LocalDateTime exitTime;

	    private Long totalMinutes;

	    private BigDecimal totalAmount;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private ParkingStatus status;

	    private Boolean emailSent;

	    private String emailMessageId;

	    @Column(nullable = false)
	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    @PrePersist
	    public void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.entryTime = LocalDateTime.now();
	        this.emailSent = false;
	    }

	    @PreUpdate
	    public void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }
}
