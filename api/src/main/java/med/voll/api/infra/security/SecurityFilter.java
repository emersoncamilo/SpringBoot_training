package med.voll.api.infra.security;

import java.io.IOException;

import med.voll.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recoveryToken(request);

        if (tokenJWT != null ){
            var subject = tokenService.getSubject(tokenJWT);
            var user = repository.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
//                System.out.println("Subject: "+subject);
//                System.out.println(tokenJWT);
        filterChain.doFilter(request, response);
    }

//    private String recoveryToken(HttpServletRequest request) {
//        var authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader == null){
//            throw new RuntimeException("Token JWT doesn't was send on header Authorization");
//        }
//        return authorizationHeader.replace("Bearer ", "");
//
//    }

    private String recoveryToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }

}
