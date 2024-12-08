package example.service;

import example.dao.DAO;
import example.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired private DAO dao;

    public String getInfo() {
        return dao.getInfo(1L);
    }
}
