package vn.vinhdeptrai.skincarebookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vinhdeptrai.skincarebookingsystem.dto.response.SlotResponse;
import vn.vinhdeptrai.skincarebookingsystem.entity.Slot;
import vn.vinhdeptrai.skincarebookingsystem.entity.Therapist;
import vn.vinhdeptrai.skincarebookingsystem.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository

public interface SlotRepository extends JpaRepository<Slot, Integer> {

    boolean existsByDateAndTime(LocalDate date, LocalTime time);

    List<Slot> findByDate(LocalDate date);

    List<Slot> findByTherapists(Therapist therapists);
}
