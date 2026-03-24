package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addReservation(@ModelAttribute("reservation") Reservation reservation) {
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
}
