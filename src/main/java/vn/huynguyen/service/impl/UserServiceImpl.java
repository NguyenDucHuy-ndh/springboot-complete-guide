package vn.huynguyen.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import vn.huynguyen.dto.request.AddressDTO;
import vn.huynguyen.dto.request.UserRequestDTO;
import vn.huynguyen.dto.response.UserDetailResponseDTO;
import vn.huynguyen.exception.ResourceNotFoundException;
import vn.huynguyen.model.Address;
import vn.huynguyen.model.User;
import vn.huynguyen.repository.UserRepository;
import vn.huynguyen.util.UserStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements vn.huynguyen.service.UserService {

    private final UserRepository userRepository;


    @Override
    public Long saveUser(UserRequestDTO requestDTO) {

        User user = User.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .dateOfBirth(requestDTO.getDateOfBirth())
                .gender(requestDTO.getGender())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .type(requestDTO.getType())
                .status(requestDTO.getStatus())
                .build();
        requestDTO.getAddresses().forEach(a ->
                user.saveAddress(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build()));


        userRepository.save(user);

        log.info("User hava save! " + user.toString());
        return user.getId();
    }

    @Override
    public void updateUser(Long userId, UserRequestDTO user) {
        User userEntity = getUserById(userId);


        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setGender(user.getGender());
        if (!user.getEmail().equals(user.getEmail())) {
            userEntity.setEmail(user.getEmail());
        }
        userEntity.setPhone(user.getPhone());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setType(user.getType());
        userEntity.setStatus(user.getStatus());
        Set<Address> addresses = convertToAddress(user.getAddresses());
        addresses.forEach(address -> address.setUser(userEntity));
        userEntity.setAddresses(addresses);

        userRepository.save(userEntity);
        log.info("User hava update! " + userEntity.toString());
    }

    @Override
    public void changeStatus(Long userId, UserStatus status) {
        User user = getUserById(userId);
        user.setStatus(status);
        userRepository.save(user);
        log.info("User hava change status! ");
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.info("User hava delete, userId:={}" + userId);
    }

    @Override
    public UserDetailResponseDTO getUser(Long userId) {
        User user = getUserById(userId);
        return UserDetailResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public List<UserDetailResponseDTO> getAllUsers(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> all = userRepository.findAll(pageable);

        if (all.hasContent()) {
            return all.getContent().stream()
                    .map(user -> UserDetailResponseDTO.builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .build())
                    .toList();
        }
        return List.of();
    }

    private Set<Address> convertToAddress(Set<AddressDTO> addresses) {
        Set<Address> result = new HashSet<>();
        addresses.forEach(addressDTO ->
                result.add(Address.builder()
                        .apartmentNumber(addressDTO.getApartmentNumber())
                        .floor(addressDTO.getFloor())
                        .building(addressDTO.getBuilding())
                        .streetNumber(addressDTO.getStreetNumber())
                        .street(addressDTO.getStreet())
                        .city(addressDTO.getCity())
                        .country(addressDTO.getCountry())
                        .addressType(addressDTO.getAddressType())
                        .build())
        );
        return result;
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
    }
}
