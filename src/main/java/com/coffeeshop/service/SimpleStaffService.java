package com.coffeeshop.service;

import com.coffeeshop.domain.user.staff.Staff;
import com.coffeeshop.domain.user.staff.StaffAssembler;
import com.coffeeshop.domain.user.staff.StaffRegisterRequestDTO;
import com.coffeeshop.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Chandan Vishwakarma
 */
@Service
@Primary
public class SimpleStaffService  implements StaffService{

    @Autowired
    StaffRepository staffRepository;

    @Override
    public Staff register(StaffRegisterRequestDTO staffRegisterRequestDTO) {
        Staff newStaff = StaffAssembler.fromRegisterDTO(staffRegisterRequestDTO);
        Staff staff = staffRepository.save(newStaff);
        return staff;
    }

    @Override
    public Staff getByID(Long id) {
        return staffRepository.findOne(id);
    }
}
