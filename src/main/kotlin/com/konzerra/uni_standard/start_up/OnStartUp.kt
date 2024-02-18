package com.konzerra.uni_standard.start_up

import com.konzerra.uni_standard.domain.user.User
import com.konzerra.uni_standard.domain.user.UserRepository
import com.konzerra.uni_standard.domain.user.UserRoles
import com.konzerra.uni_standard.domain.user.role.Role
import com.konzerra.uni_standard.domain.user.role.RoleRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class OnStartup(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val list: List<Role> = listOf(
            Role(
                name = UserRoles.USER
            ),
            Role(
                name = UserRoles.ADMIN
            ),
            Role(
                name = UserRoles.DATA_ACCESS
            )
        )
        //check if role exists before saving
        list.forEach {
            try{
                roleRepository.findRoleByName(it.name)
            }catch(e:RuntimeException) {
                roleRepository.save(it)
            }

        }


        val user = User(
            name = "Ruslan",
            email = "konzerra@gmail.com",
            password = passwordEncoder.encode("123"),
            roles = mutableSetOf(
                roleRepository.findRoleByName(UserRoles.ADMIN),
                roleRepository.findRoleByName(UserRoles.USER)
            )
        )


        try{
            userRepository.findByEmail("konzerra@gmail.com")
        }
        catch(e:RuntimeException){
            userRepository.save(user)
        }
    }

}