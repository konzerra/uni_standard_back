package com.konzerra.uni_standard.domain.university

import com.konzerra.uni_standard.domain.report.Report
import com.konzerra.uni_standard.domain.university.data.AcademicStaffTraining
import com.konzerra.uni_standard.domain.university.data.ProgramNumber
import com.konzerra.uni_standard.domain.university.data.StudentNumber
import com.konzerra.uni_standard.domain.user.User
import jakarta.persistence.*

@Entity
class University(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var version: String = "",

    // 1 Наменование учреждения
    var name: String = "",

    // 2 Адрес
    var address: String = "",

    // 3 Год образования
    var yearFounded: Int = 0,

    // 4 ФИО ректора
    var rectorName: String = "",

    // 5 Количество учебных подразделений
    var numOfEducationalUnits: Long = 0,

    // 6 Контингент обучающихся по программам:
    @OneToOne(cascade = [CascadeType.ALL])
    var studentNumber: StudentNumber = StudentNumber(),

    // 7 Количество реализуемых образовательных программах:
    @OneToOne(cascade = [CascadeType.ALL])
    var programNumber: ProgramNumber = ProgramNumber(),

    // 8. Сведения о подготовке научно-педагогических кадров:
    @OneToOne(cascade = [CascadeType.ALL])
    var academicStaffTraining: AcademicStaffTraining = AcademicStaffTraining(),

    // 9. Количество принятых на 1 курс студентов в отчетном году
    var numOfFirstYearStudents: Long = 0,

    // 10. Количество выпускников в отчетном году
    var numOfGraduates: Long = 0,

    // 11. Общая площадь учебного фонда
    var totalAreaOfEducationalFund: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH])
    var manager: User,

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var reports: List<Report> = emptyList(),
)
