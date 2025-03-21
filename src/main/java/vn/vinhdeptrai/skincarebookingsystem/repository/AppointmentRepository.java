package vn.vinhdeptrai.skincarebookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.vinhdeptrai.skincarebookingsystem.entity.Appointment;
import vn.vinhdeptrai.skincarebookingsystem.entity.SlotDetail;
import vn.vinhdeptrai.skincarebookingsystem.entity.User;
import vn.vinhdeptrai.skincarebookingsystem.enums.AppointmentStatus;
import vn.vinhdeptrai.skincarebookingsystem.enums.PaymentStatus;
import vn.vinhdeptrai.skincarebookingsystem.enums.SlotStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByUser(User user);

    Optional<Appointment> findByTxnRef(String txnRef);

    List<Appointment> findByAppointmentStatusAndCancelAtBefore(AppointmentStatus appointmentStatus, LocalDateTime cancelAtBefore);

    List<Appointment> findByUserAndPaymentStatus(User user, PaymentStatus paymentStatus);

    List<Appointment> findByAppointmentStatusAndCreateAtBefore(AppointmentStatus appointmentStatus, LocalDateTime createAtBefore);

    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.slotDetail.therapist.id = :therapistId")
    void deleteAppointmentsByTherapistId(@Param("therapistId") int therapistId);
}
