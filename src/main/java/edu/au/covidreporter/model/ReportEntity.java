package main.java.edu.au.covidreporter.model;

import javax.persistence.*;

/**
 * JPA Entity for 'report' database table.
 */
@Entity
@Table(name = "report")
public class ReportEntity {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "file_name", nullable = false)
    private String file_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
