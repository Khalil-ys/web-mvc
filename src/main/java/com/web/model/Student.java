package com.web.model;

import com.web.validation.MyRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotEmpty(message ="Telebe adi bosh olmamalidir!")
    @Size(min=2,message="Telebe adin min 2 sinvol olmalidir!")
    @Size(max=20,message="Telebe adin max 20 sinvol olmalidir!")
    private String name;
    @NotNull
    @NotEmpty(message ="Telebe soyadi bosh olmamalidir!")
    @Size(min=2,message="Telebe soyadin min 2 sinvol olmalidir!")
    @Size(max=20,message="Telebe soyadin max 20 sinvol olmalidir!")
    private String surname;
    @Min(value = 1,message = "Telebe sinifi min 1 ola biler")
    @Max(value = 11,message = "Telebe sinifi max 11 ola biler")
    private Integer studentClass;
    @Past(message = "Tevellud kecmis zaman olmalidir")
    private Date birthday;
    @Pattern(regexp = "[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,4}",message="Email duzgun formatda deyil!!")
    private String email;
    @MyRule
    private String courseCode;
    @Pattern(regexp="[0-9]{3}-[0-9]{3}-[0-9]{4}",message="Telefon duzgun formatda yazilmalidir! {055-555-5555}")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @ManyToMany
    private List<Language>languages;

    public Student(int id, String name, String surname,int studentClass) {
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.studentClass=studentClass;
    }
}
