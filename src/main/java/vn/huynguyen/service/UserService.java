package vn.huynguyen.service;

import vn.huynguyen.dto.request.UserRequestDTO;

public interface UserService {

    int addUser(UserRequestDTO user);
}
