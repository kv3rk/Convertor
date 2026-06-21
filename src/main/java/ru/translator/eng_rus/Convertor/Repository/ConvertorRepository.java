package ru.translator.eng_rus.Convertor.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.translator.eng_rus.Convertor.DTO.ConvertorDTO;
import ru.translator.eng_rus.Convertor.Entity.ConvertorEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConvertorRepository extends JpaRepository<ConvertorEntity, UUID> {

    @Query(value = """
             select new ru.translator.eng_rus.Convertor.DTO.ConvertorDTO(
                c.id,
                c.time,
                c.wrongString,
                c.rightString
             )
             from ConvertorEntity c
             order by c.time desc
            """,
            nativeQuery = false
    )
    List<ConvertorDTO> recentHistoryOf3(Pageable pageable);

}
