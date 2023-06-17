package com.konzerra.uni_standard.domain.user


import com.konzerra.uni_standard.domain.user.role.Role
import jakarta.persistence.*


@Entity
@Table(name = "app_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var name:String,
    @Column(unique = true)
    var email:String,
    var password:String,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH])
    var roles:MutableSet<Role> = mutableSetOf(),
    //@OneToMany(mappedBy = "owner", cascade = [CascadeType.REFRESH])
   // var articles:Set<University> = setOf(),
)