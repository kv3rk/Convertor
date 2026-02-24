package ru.translator.eng_rus.DTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.translator.eng_rus.POJO.WrongStringPOJO;
import ru.translator.eng_rus.util.GetConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Repository
public class DTOTranslate {
    private final GetConnection getConnection;

    public DTOTranslate(GetConnection getConnection) {
        this.getConnection = getConnection;
    }

    public void save(WrongStringPOJO pojo) {
        String sql = "insert into wrongstring (id, localdatetime, wrongstring, " +
                "rightstring) values (?,?,?,?);";
        try (Connection connection = getConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pojo.getId());
            statement.setObject(2, pojo.getLocalDateTime());
            statement.setString(3, pojo.getWrongString());
            statement.setString(4, pojo.getRightString());
            statement.executeUpdate();
            log.info("Saved bean {} in database", pojo.getWrongString());
            log.info("Closed connection to database");
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.info("Closed connection to database");
        }
    }
}
