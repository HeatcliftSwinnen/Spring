package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.models.entity.Studio;
import be.technifutur.spring.demo.repository.AddressRepository;
import be.technifutur.spring.demo.repository.StudioRepository;
import be.technifutur.spring.demo.service.StudioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    private final AddressRepository addressRepository;

    public StudioServiceImpl(
            StudioRepository studioRepository,
            AddressRepository addressRepository
    ) {
        this.studioRepository = studioRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Ajoute un studio à la base de données.
     *
     * @param studio Le studio à ajouter à la base de données.
     * @return L'ID du studio ajouté.
     */
    @Override
    public Long add(Studio studio) {
        return studioRepository.save( studio ).getId();
    }

    @Override
    public List<Studio> getAll() {
        // Récupère la liste de tous les studios
        return studioRepository.findAll();
    }

    @Override
    public Studio getOne(Long id) {
        // Récupère un studio en fonction de son ID
        return studioRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(id, Studio.class));
    }

    @Override
    public void update(Long id, Studio studio) {
        // Met à jour les informations d'un studio en fonction de son ID
        Studio entity = getOne( id );

        entity.setName( studio.getName() );
        entity.setAddress( studio.getAddress() );

        studioRepository.save( entity );
    }

    @Override
    public void delete(Long id) {
        // Supprime un studio en fonction de son ID
        studioRepository.delete( getOne(id) );
    }
}
