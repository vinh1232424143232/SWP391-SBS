package vn.vinhdeptrai.skincarebookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import vn.vinhdeptrai.skincarebookingsystem.enums.AppointmentStatus;
import vn.vinhdeptrai.skincarebookingsystem.enums.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name ="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    String note;
    double price;
    double depositAmount;
    @ManyToOne
    @JoinColumn(name ="userId")
    User user;

    @OneToOne
    @JoinColumn(name = "slotDetailId")
    SlotDetail slotDetail;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    Service service;

    @Enumerated(EnumType.STRING)
    AppointmentStatus appointmentStatus;

    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;


}
