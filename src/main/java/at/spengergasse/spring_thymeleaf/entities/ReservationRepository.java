package at.spengergasse.spring_thymeleaf.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByDeviceIdOrderByReservationTimeAsc(String deviceId);
    List<Reservation> findAllByOrderByReservationTimeAsc();

    List<Reservation> findByDeviceIdAndReservationTimeBetween(String deviceId, LocalDateTime start, LocalDateTime end);

    List<Reservation> findByPatientIdAndReservationTimeBetween(Long patientId, LocalDateTime start, LocalDateTime end);

    boolean existsByDeviceIdAndReservationTime(String deviceId, LocalDateTime reservationTime);

    boolean existsByPatientIdAndReservationTime(Long patientId, LocalDateTime reservationTime);
}
