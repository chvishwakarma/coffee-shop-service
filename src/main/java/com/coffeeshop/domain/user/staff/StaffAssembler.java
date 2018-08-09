package com.coffeeshop.domain.user.staff;


import com.coffeeshop.constant.StatusType;
import com.coffeeshop.constant.UserType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class StaffAssembler {


    public static Staff fromRegisterDTO(StaffRegisterRequestDTO staffRegisterRequestDTO){
        final Staff staff = new Staff();
        staff.setType(UserType.STAFF);
        staff.setStatus(StatusType.ACTIVE);
        staff.setEmail(staffRegisterRequestDTO.getEmail());
        staff.setPassword( new BCryptPasswordEncoder().encode(staffRegisterRequestDTO.getPassword()));
        staff.setName(staffRegisterRequestDTO.getName());
        staff.setGender(staffRegisterRequestDTO.getGender());
        staff.setMobile(staffRegisterRequestDTO.getContactNumber());
        staff.setBirthDate(staffRegisterRequestDTO.getBirthDate());
        return staff;
    }

    public static StaffWebDTO fromStaff(Staff staff){
        final StaffWebDTO staffWebDTO = new StaffWebDTO();
        staffWebDTO.setName(staff.getName());
        staffWebDTO.setId(staff.getId());
        staffWebDTO.setEmail(staff.getEmail());
        staffWebDTO.setContactNumber(staff.getMobile());
        staffWebDTO.setBirthDate(staff.getBirthDate());
        staffWebDTO.setGender(staff.getGender());
        return staffWebDTO;
    }
}
