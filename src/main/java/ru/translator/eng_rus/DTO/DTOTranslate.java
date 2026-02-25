package ru.translator.eng_rus.DTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.translator.eng_rus.POJO.WrongStringPOJO;
import ru.translator.eng_rus.util.GetConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            statement.setString(1, pojo.getId());
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

    public List<WrongStringPOJO> findAll() {
        String sql = "select * from wrongstring;";
        List<WrongStringPOJO> fullList = new ArrayList<>();
        try (Connection connection = getConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                LocalDateTime localDateTime = rs.getTimestamp("localdatetime").
                        toLocalDateTime();
                String wrong = rs.getString("wrongstring");
                String right = rs.getString("rightstring");
                fullList.add(new WrongStringPOJO(id, localDateTime, wrong, right));
            }
            log.info("Got all pojo objects from database");
            log.info("Closed connection to database");
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.info("Closed connection to database");
        }
        return fullList;
    }

    public List<WrongStringPOJO> findRecent() {
        String sql = "select wrongstring, rightstring from wrongstring group by id, \n" +
                "localdatetime order by localdatetime  desc limit 2 offset 0;";
        List<WrongStringPOJO> recentList = new ArrayList<>();
        try (Connection connection = getConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String id = null;
                LocalDateTime localDateTime = null;
                String wrong = rs.getString("wrongstring");
                String right = rs.getString("rightstring");
                recentList.add(new WrongStringPOJO(id, localDateTime, wrong, right));
            }
            log.info("Got 2 recent pojo objects from database");
            log.info("Closed connection to database");
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.info("Closed connection to database");
        }
        return recentList;
    }

    public void removeAll() {
        String sql = "delete from wrongstring *;";
        try (Connection connection = getConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            log.info("Deleted all objects from database");
            log.info("Closed connection to database");
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.info("Closed connection to database");
        }
    }

}
