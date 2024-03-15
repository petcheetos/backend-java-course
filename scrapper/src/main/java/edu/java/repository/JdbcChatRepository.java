package edu.java.repository;

import edu.java.dto.ChatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcChatRepository implements ChatRepository {

    private final JdbcTemplate jdbcTemplate;
    @Override
    @Transactional
    public void add(Long chatId) {
        jdbcTemplate.update("insert into chat (id) values (?)", chatId);
    }

    @Override
    @Transactional
    public void remove(Long chatId) {
        jdbcTemplate.update("delete from chat where id = (?)", chatId);
    }

    @Override
    @Transactional
    public List<ChatDTO> findAll() {
        return jdbcTemplate.query("select (*) from chat", new ChatMapper());
    }

    @Override
    @Transactional
    public boolean exist(Long chatId) {
        Integer count = jdbcTemplate.queryForObject("select count(*) from chat where id = ?", Integer.class, chatId);
        return count != null && count > 0;
    }
}
