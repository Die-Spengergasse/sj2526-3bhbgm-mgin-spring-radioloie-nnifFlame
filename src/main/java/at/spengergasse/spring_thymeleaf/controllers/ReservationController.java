package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final PatientRepository patientRepository;
    private final DeviceRepository deviceRepository;

    public ReservationController(ReservationRepository reservationRepository, 
                                 PatientRepository patientRepository, 
                                 DeviceRepository deviceRepository) {
        this.reservationRepository = reservationRepository;
        this.patientRepository = patientRepository;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("devices", deviceRepository.findAll());
        return "add_reservation";
    }

    @PostMapping("/add")
    public String addReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, Model model) {
        // Überpgrüfung auf Überschneidungen
        if (reservation.getDevice() != null && reservation.getReservationTime() != null) {
            if (reservationRepository.existsByDeviceIdAndReservationTime(reservation.getDevice().getId(), reservation.getReservationTime())) {
                bindingResult.addError(new ObjectError("reservation", "Für dieses Gerät existiert bereits ein Termin zur gleichen Zeit."));
            }
        }
        
        if (reservation.getPatient() != null && reservation.getReservationTime() != null) {
            if (reservationRepository.existsByPatientIdAndReservationTime(reservation.getPatient().getId(), reservation.getReservationTime())) {
                bindingResult.addError(new ObjectError("reservation", "Für diesen Patienten existiert bereits ein Termin zur gleichen Zeit."));
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("patients", patientRepository.findAll());
            model.addAttribute("devices", deviceRepository.findAll());
            return "add_reservation";
        }
        
        reservationRepository.save(reservation);
        return "redirect:/reservations/list?deviceId=" + reservation.getDevice().getId();
    }

    @GetMapping("/list")
    public String listByDevice(@RequestParam(value = "deviceId", required = false) String deviceId, Model model) {
        model.addAttribute("devices", deviceRepository.findAll());
        if (deviceId != null && !deviceId.isEmpty()) {
            model.addAttribute("reservations", reservationRepository.findByDeviceIdOrderByReservationTimeAsc(deviceId));
            model.addAttribute("selectedDeviceId", deviceId);
        }
        return "reslist";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ungültige Reservierungs-ID: " + id));
        model.addAttribute("reservation", reservation);
        return "res_details";
    }
}
