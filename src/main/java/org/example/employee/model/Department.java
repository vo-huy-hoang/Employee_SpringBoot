package org.example.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động
    @Column(name = "id") // tên cho cột ở trong table
    private int id;
    @Column(name = "name")
    private String name;
    public Department (Integer id) {
        this.id = id;
    }
}
