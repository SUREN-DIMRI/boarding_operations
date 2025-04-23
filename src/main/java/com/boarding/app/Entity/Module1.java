package com.boarding.app.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "module1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Module1 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "module1_seq")
    @SequenceGenerator(name = "module1_seq", sequenceName = "module1_seq", allocationSize = 1)
    private Long id;

    private String vesselBoatName;

    @Column(name = "imo_mmsi_reg_no")
    private String imoMmsiRegNo;

    private String typeOfVesselBoat;

    private LocalDateTime lpcDateTimeDeparture;
    private LocalDateTime npcDateTimeDeparture;

    private String posnOfBoardingTime;
    private String masterOwnerTandelName;
    private String masterOwnerTandelContactNo;
    private String localAgentName;
    private String localAgentContactNo;

    private String typeOfFishingNet;
    private String typeOfFishHeld;
    private String colourCodingOfBoat;
    private String fuelAndWaterHeldOnboard;
    private String violations;
    private String crewAndNationality;
    private String communicationAndSafetyEquipment;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] photoVesselBoat;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] photoVideoVesselBoat;

    @Lob
    private String anyOtherInformation;
}
