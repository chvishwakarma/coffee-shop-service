package com.coffeeshop.service;

import com.coffeeshop.domain.user.staff.Staff;
import com.coffeeshop.domain.user.staff.StaffRegisterRequestDTO;

/**
 * Created by superuser on 09-08-2018.
 */
public interface StaffService {
    Staff register(StaffRegisterRequestDTO staffRegisterRequestDTO);
    Staff getByID(Long id);
}
