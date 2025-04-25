package vn.huynguyen.service;

import vn.huynguyen.dto.request.UserRequestDTO;
import vn.huynguyen.dto.response.UserDetailResponseDTO;
import vn.huynguyen.util.UserStatus;

import java.util.List;

public interface UserService {

    Long saveUser(UserRequestDTO user);

    void updateUser(Long userId, UserRequestDTO user);

    void changeStatus(Long userId, UserStatus status);

    void deleteUser(Long userId);

    UserDetailResponseDTO getUser(Long userId);

    List<UserDetailResponseDTO> getAllUsers(int pageNo, int pageSize);

}
