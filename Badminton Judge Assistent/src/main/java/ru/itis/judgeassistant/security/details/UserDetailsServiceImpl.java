package ru.itis.judgeassistant.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.judgeassistant.models.users.Admin;
import ru.itis.judgeassistant.models.users.Judge;
import ru.itis.judgeassistant.models.users.User;
import ru.itis.judgeassistant.repositories.JudgeRepository;
import ru.itis.judgeassistant.repositories.AdminRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final JudgeRepository judgeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Judge> judge = judgeRepository.findByLogin(username);
        Optional<Admin> admin = adminRepository.findByLogin(username);

        User user = null;
        if (judge.isPresent()) {
            user = judge.get();
        } else if (admin.isPresent()) {
            user = admin.get();
        }
        if (user != null) {
            return new UserDetailsImpl(user);
        } else {
            throw new UsernameNotFoundException(
                    "Пользователя с логином <" + username + "> не найдено"
            );
        }
    }
}
