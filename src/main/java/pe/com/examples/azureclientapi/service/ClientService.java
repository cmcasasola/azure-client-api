package pe.com.examples.azureclientapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.examples.azureclientapi.dto.ClientDto;
import pe.com.examples.azureclientapi.entity.Client;
import pe.com.examples.azureclientapi.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientDto save(ClientDto clientDto) {
        Client client = this.clientRepository
                .save(Client.builder().id(clientDto.getId()).name(clientDto.getName()).lastName(clientDto.getLastName())
                        .age(clientDto.getAge()).birthDate(clientDto.getBirthDate()).build());

        return ClientDto.builder().id(client.getId()).name(client.getName()).lastName(client.getLastName())
                .age(client.getAge()).birthDate(client.getBirthDate()).build();
    }

    public ClientDto findClient(String id) {
        return this.clientRepository.findById(id)
                .map(client -> ClientDto.builder().id(client.getId()).name(client.getName())
                        .lastName(client.getLastName()).age(client.getAge()).birthDate(client.getBirthDate()).build())
                .get();
    }

    public List<ClientDto> listClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(client -> ClientDto.builder().id(client.getId()).name(client.getName())
                        .lastName(client.getLastName()).age(client.getAge()).birthDate(client.getBirthDate()).build())
                .collect(Collectors.toList());
    }

    public void deleteClient(String id) {
        this.clientRepository.deleteById(id);
    }
}
