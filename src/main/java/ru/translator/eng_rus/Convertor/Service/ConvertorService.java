package ru.translator.eng_rus.Convertor.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.translator.eng_rus.Convertor.DTO.ConvertorDTO;
import ru.translator.eng_rus.Convertor.Dictionary.TranslateWord;
import ru.translator.eng_rus.Convertor.Entity.ConvertorEntity;
import ru.translator.eng_rus.Convertor.Repository.ConvertorRepository;
import ru.translator.eng_rus.common.mapper.ConvertorMapper;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ConvertorService {
    private final TranslateWord translateWord;
    private final ConvertorRepository convertorRepository;
    private final ConvertorMapper convertorMapper;

    public ConvertorService(TranslateWord translateWord,
                            ConvertorRepository convertorRepository,
                            ConvertorMapper convertorMapper) {

        this.translateWord = translateWord;
        this.convertorRepository = convertorRepository;
        this.convertorMapper = convertorMapper;
    }

    @Transactional
    public ConvertorDTO save(ConvertorDTO convertorDTO) {

        String rightString = translateWord.translateWord(convertorDTO.wrongString());

        ConvertorEntity convertorEntity = new ConvertorEntity();

        convertorEntity.setWrongString(convertorDTO.wrongString());
        convertorEntity.setRightString(rightString);

        convertorRepository.save(convertorEntity);

        log.info("Saved entity with wrong text and right text:\n{}\n{}",
                convertorDTO.wrongString(), rightString);

        ConvertorDTO convertorDTOResult = convertorMapper.convertorEntityToDTO(convertorEntity);

        log.info("Returning result right string in DTO format: \n{}",
                convertorDTO.rightString());

        return convertorDTOResult;
    }

    @Transactional
    public List<ConvertorDTO> getAll() {

        List<ConvertorDTO> convertorDTOList = convertorRepository.findAll()
                .stream()
                .map(convertorMapper::convertorEntityToDTO)
                .toList();

        log.info("Got all converted strings and returning them in DTO list");

        return convertorDTOList;
    }

    @Transactional
    public List<ConvertorDTO> getRecentHistory() {

        Pageable pageable = PageRequest.of(0, 3);
        List<ConvertorDTO> convertorDTOList = convertorRepository.recentHistoryOf3(pageable);

        log.info("Got last 3 converted strings and returning them in DTO list");

        return convertorDTOList;
    }

    @Transactional
    public void removeAll() {

        convertorRepository.deleteAll();

        log.info("Removed all converted Strings");

    }

    @Transactional
    public void removeNote(UUID id) {

        convertorRepository.deleteById(id);

        log.info("Removed string with id [{}]",
                id);

    }
}
