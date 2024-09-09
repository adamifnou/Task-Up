package com.example.TaskUP.controllers;

import com.example.TaskUP.dto.AdminDto;
import com.example.TaskUP.dto.AthenticatedAdminDto;
import com.example.TaskUP.model.Admin;
import com.example.TaskUP.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final AdminService adminService;


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // save admin
    @PostMapping("/admin/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody  AdminDto adminDto) {
       try {
           adminService.saveAdmin(adminDto);
           return ResponseEntity.ok("Admin registered successfully"+ adminDto);
       } catch (Exception e) {
           return ResponseEntity.badRequest().body("Error: " + e.getMessage());
       }
    }
    // authenticate admin
    @PostMapping("/admin/authenticate")
    public ResponseEntity<Object> authenticateAdmin(@RequestBody AdminDto adminDto) {
        try {
            System.out.println(adminService.authenticateAdmin(adminDto.getBadgeNumber(), adminDto.getPassword()));
            if (adminService.authenticateAdmin(adminDto.getBadgeNumber(), adminDto.getPassword())) {

                AthenticatedAdminDto admin = new AthenticatedAdminDto();
                Admin authenticatedAdmin = adminService.findByBadgeNumber(adminDto.getBadgeNumber());
                admin.setBadgeNumber(authenticatedAdmin.getBadgeNumber());
                admin.setId(authenticatedAdmin.getId());
                return new ResponseEntity<>(
                        admin,
                        org.springframework.http.HttpStatus.OK
                );
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
