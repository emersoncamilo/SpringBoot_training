package med.api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAutenticationService implements UserDetailsService {
    
    @Autowired
    private UserRepository respository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return respository.findByLogin(userName);

    }

}
