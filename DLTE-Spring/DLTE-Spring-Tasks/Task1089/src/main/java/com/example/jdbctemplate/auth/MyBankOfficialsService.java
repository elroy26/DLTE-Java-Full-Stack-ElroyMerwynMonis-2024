package com.example.jdbctemplate.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankOfficialsService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    Level 3
//    public MyBankOfficials signingUp(MyBankOfficials myBankUsers){
//        jdbcTemplate.update("insert into  my_bank_officials values(?,?,?,?,?,?,?)",new Object[]{
//                myBankUsers.getName(),myBankUsers.getUsername(),
//                myBankUsers.getPassword(),myBankUsers.getEmail(),
//                myBankUsers.getContact(),myBankUsers.getAadhaar(),
//
//        });
//        return myBankUsers;
//    }

    public MyBankOfficials signingUp(MyBankOfficials myBankOfficials){
        int ack = jdbcTemplate.update("insert into my_bank_officials values(?,?,?,?,?,?)",new Object[]{
                myBankOfficials.getUsername(),
                myBankOfficials.getPassword(),
                myBankOfficials.getRole(),
                myBankOfficials.getAddress(),
                myBankOfficials.getContact(),
                myBankOfficials.getEmail()
        });
        return myBankOfficials;
    }
    public MyBankOfficials findByUsername(String username){
        MyBankOfficials myBankOfficials = jdbcTemplate.queryForObject("select * from my_bank_officials where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankOfficials.class));
        return myBankOfficials;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankOfficials officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}
