package com.konzerra.uni_standard.domain.user.role



import com.konzerra.uni_standard.domain.user.User
import jakarta.persistence.*

@Entity
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    @Column(unique = true)
    var name:String,
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH])
    var users:MutableSet<User> = mutableSetOf()
)