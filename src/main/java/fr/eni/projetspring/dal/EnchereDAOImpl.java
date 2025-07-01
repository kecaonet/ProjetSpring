package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Enchere;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class EnchereDAOImpl implements EnchereDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String SELECT_ALL = "select * from encheres";

    public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Enchere enchere) {

    }

    @Override
    public Enchere findById(int id) {
        return null;
    }

    @Override
    public List<Enchere> findAll() {
        return jdbcTemplate.getJdbcTemplate().query(SELECT_ALL, new BeanPropertyRowMapper<>(Enchere.class));
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

        }
    }