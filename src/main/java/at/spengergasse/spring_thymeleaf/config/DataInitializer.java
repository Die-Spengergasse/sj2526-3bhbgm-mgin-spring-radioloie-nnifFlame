package at.spengergasse.spring_thymeleaf.config;

import at.spengergasse.spring_thymeleaf.entities.Device;
import at.spengergasse.spring_thymeleaf.entities.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(DeviceRepository deviceRepository) {
        return args -> {
            if (deviceRepository.count() == 0) {
                deviceRepository.save(new Device("MR-01", "MR", "Raum 101"));
                deviceRepository.save(new Device("CT-01", "CT", "Raum 102"));
                deviceRepository.save(new Device("RTG-01", "Röntgen", "Raum 103"));
                deviceRepository.save(new Device("US-01", "Ultraschall", "Raum 104"));
            }
        };
    }
}
