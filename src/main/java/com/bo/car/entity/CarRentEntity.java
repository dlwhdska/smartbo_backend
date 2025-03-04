package com.bo.car.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.bo.member.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_rent")
@DynamicInsert
public class CarRentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity member;
    
    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private CarEntity car;
    
    @Column(nullable = false)
    private Date reqDate;
    
    @Column(nullable = false)
    private String startDate;
    
    @Column(nullable = false)
    private String endDate;
    
    @Column(length = 200)
    private String purpose;
    
    @Column(nullable = false, length = 2)
    private Long status;
    
    @Column(length = 200)
    private String reject;

    @PrePersist
    protected void onCreate() {
        if (this.reqDate == null) {
            this.reqDate = Date.valueOf(LocalDate.now());
        }
        if (this.status == null) {
            this.status = 0L; 
        }
    }
    
    public void modifyCarRentStatus(Long status) {
        this.status = status;
    }
    
    public void modifyCarRentReject(String reject) {
        this.reject = reject;
    }
}
