package com.fqts.user.service;

import com.fqts.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CronSchedular {
    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(CronSchedular.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteInactiveUsers() {
        Date currentDate = new Date();
        long millisIn15Days = 15L * 24 * 60 * 60 * 1000;
        Date cutoffDate = new Date(currentDate.getTime() - millisIn15Days);
        log.info("Cutoff date: " + cutoffDate);
        int deletedCount = userRepository.deleteInactiveUsers(cutoffDate);
        log.info("Deleted " + deletedCount + " inactive users.");
    }
}
