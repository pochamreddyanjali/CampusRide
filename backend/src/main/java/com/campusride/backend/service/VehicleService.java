package com.campusride.backend.service;

import com.campusride.backend.entity.Vehicle;
import com.campusride.backend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private final String uploadDir = "C:/Users/dilee/OneDrive/Desktop/Mini-Project/CampusRide/backend/uploads/";


    private String saveFile(MultipartFile file, String folder) throws IOException {

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String folderPath = uploadDir + folder + "/";

        // Create folder if it doesn't exist
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = folderPath + fileName;
        File destination = new File(filePath);

        file.transferTo(destination);

        return filePath;
    }


    public Vehicle registerVehicle(
            Vehicle vehicle,
            MultipartFile collegeId,
            MultipartFile license,
            MultipartFile rc) throws IOException {

        String collegePath = saveFile(collegeId, "college");
        String licensePath = saveFile(license, "license");
        String rcPath = saveFile(rc, "rc");

        vehicle.setCollegeIdImage(collegePath);
        vehicle.setLicenseImage(licensePath);
        vehicle.setRcImage(rcPath);
        vehicle.setStatus("PENDING");

        return vehicleRepository.save(vehicle);
    }
}
