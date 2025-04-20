package vn.huynguyen.service.impl;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import vn.huynguyen.dto.request.UserRequestDTO;

@Service
public class UserService implements vn.huynguyen.service.UserService {
    @Override
    public int addUser(UserRequestDTO requestDTO) {

        if(!requestDTO.getFirstName().equals("baby")) {
            throw new ResourceAccessException("First name must be baby");
        }
        return 0;
    }
}
