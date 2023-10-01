package br.com.waldir.userserviceapi.service;

import br.com.waldir.userserviceapi.entity.User;
import br.com.waldir.userserviceapi.mapper.UserMapper;
import br.com.waldir.userserviceapi.repository.UserRepository;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.responses.UserResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static br.com.waldir.userserviceapi.creator.CreatorUtils.generateMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Test
    void whenCallFindByIdValidThenReturnUserResponse() {

        when(repository.findById(anyString())).thenReturn(Optional.of(new User()));
        when(mapper.fromEntity(any(User.class))).thenReturn(generateMock(UserResponse.class));

        final var response = service.findById("1");

        assertNotNull(response);
        assertEquals(UserResponse.class, response.getClass());
        verify(repository, times(1)).findById(anyString());
        verify(mapper, times(1)).fromEntity(any(User.class));
    }

    @Test
    void whenCallFindByIdWithInvalidIdThenThrowResourceNotFoundException() {

        when(repository.findById(anyString())).thenReturn(Optional.empty());

        var exception = assertThrows(ResourceNotFoundException.class,
                () -> service.findById("1")
        );

        verify(repository, times(1)).findById(anyString());
        verify(mapper, times(0)).fromEntity(any(User.class));
        assertEquals("Object not found. Id: 1, Type UserResponse", exception.getMessage());
    }

    @Test
    void whenCallFindAllThenReturnListOfUserResponse() {

        when(repository.findAll()).thenReturn(List.of(new User(), new User()));
        when(mapper.fromEntity(any(User.class))).thenReturn(generateMock(UserResponse.class));

        final var response = service.findAll();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(UserResponse.class, response.get(0).getClass());

        verify(repository, times(1)).findAll();
        verify(mapper, times(response.size())).fromEntity(any(User.class));
    }

    @Test
    void whenCallSaveThenSuccess() {

        final var request = generateMock(CreateUserRequest.class);
        when(mapper.fromRequest(any())).thenReturn(new User());
        when(encoder.encode(anyString())).thenReturn("encoded");
        when(repository.save(any(User.class))).thenReturn(new User());
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        service.save(request);

        verify(mapper).fromRequest(request);
        verify(encoder).encode(request.password());
        verify(repository).save(any(User.class));
        verify(repository).findByEmail(request.email());
    }

    @Test
    void whenCallSaveWithInvalidEmailThenThrowDataIntegrityViolationException() {

        final var request = generateMock(CreateUserRequest.class);
        final var entity = generateMock(User.class);

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));

        var exception = assertThrows(DataIntegrityViolationException.class, () -> {
            service.save(request);
        });

        assertEquals(DataIntegrityViolationException.class, exception.getClass());
        assertEquals("Email [ " + request.email() + " ] already exists.", exception.getMessage());
        verify(mapper, times(0)).fromRequest(request);
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));
        verify(repository).findByEmail(request.email());

    }

    @Test
    void whenCallUpdateWithInvalidIdThenThrowResourceNotFoundException() {

        final var request = generateMock(UpdateUserRequest.class);

        when(repository.findById(anyString())).thenReturn(Optional.empty());

        var exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.update("1", request);
        });

        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals("Object not found. Id: 1, Type UserResponse", exception.getMessage());
        verify(mapper, times(0)).update(any(), any());
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));
        verify(repository).findById(anyString());
    }

    @Test
    void whenCallUpdateWithInvalidEmailThenThrowDataIntegrityViolationException() {

        final var entityId = "1";
        final var entity = generateMock(User.class);
        final var request = generateMock(UpdateUserRequest.class);

        when(repository.findById(entityId)).thenReturn(Optional.of(entity));
        when(repository.findByEmail(request.email())).thenReturn(Optional.of(entity.withId("12")));

        var exception = assertThrows(DataIntegrityViolationException.class, () -> {
            service.update(entityId, request);
        });

        assertEquals(DataIntegrityViolationException.class, exception.getClass());
        assertEquals("Email [ " + request.email() + " ] already exists.", exception.getMessage());
        verify(mapper, times(0)).update(any(), any());
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));
        verify(repository).findById(anyString());
    }

    @Test
    void whenCallUpdateWithValidParamsThenGetSuccess() {

        final var entityId = "1";
        final var entity = generateMock(User.class).withId(entityId);
        final var request = generateMock(UpdateUserRequest.class);

        when(repository.findById(anyString())).thenReturn(Optional.of(entity));
        when(repository.findByEmail(request.email())).thenReturn(Optional.of(entity));
        when(mapper.update(any(), any())).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(entity);

        service.update(entityId, request);

        verify(mapper).update(request, entity);
        verify(encoder).encode(request.password());
        verify(repository).findById(anyString());
        verify(repository).findByEmail(request.email());
        verify(repository).save(any(User.class));
    }

}