package com.example.TaskUP.services;

import com.example.TaskUP.dto.AdminDto;
import com.example.TaskUP.model.Admin;
import com.example.TaskUP.repository.AdminRepository;

public interface AdminService {
Admin saveAdmin(AdminDto adminToSave);
Admin findByBadgeNumber(String badgeNumber);
boolean alreadyExists(String badgeNumber);
boolean authenticateAdmin(String badgeNumber, String rawPassword);
}
