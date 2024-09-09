package com.example.TaskUP.services;

import com.example.TaskUP.dto.AdminDto;
import com.example.TaskUP.model.Admin;
import com.example.TaskUP.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin saveAdmin(AdminDto adminToSave) {
        Admin admin = new Admin();
        // check Admin already exists
        if (alreadyExists(adminToSave.getBadgeNumber())) {
            throw new RuntimeException("Admin already exists");
        }
        admin.setBadgeNumber(adminToSave.getBadgeNumber());
        admin.setPassword(passwordEncoder.encode(adminToSave.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public boolean alreadyExists(String badgeNumber) {
        return adminRepository.findByBadgeNumber(badgeNumber).isPresent();
    }

    @Override
    public boolean authenticateAdmin(String badgeNumber, String rawPassword) {
        Optional<Admin> admin = adminRepository.findByBadgeNumber(badgeNumber);
        return admin.isPresent() && passwordEncoder.matches(rawPassword, admin.get().getPassword());
    }

    @Override
    public Admin findByBadgeNumber(String badgeNumber) {
        return adminRepository.findByBadgeNumber(badgeNumber).orElse(null);
    }
}
