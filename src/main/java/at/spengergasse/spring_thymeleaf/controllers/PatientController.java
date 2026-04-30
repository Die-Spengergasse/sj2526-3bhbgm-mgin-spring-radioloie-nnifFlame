package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public String listPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patlist";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add_patient";
    }

    @PostMapping("/register")
    public String registerPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_patient";
        }
        patientRepository.save(patient);
        return "redirect:/patients/list";
    }
    @PostMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients/list";
    }
}
