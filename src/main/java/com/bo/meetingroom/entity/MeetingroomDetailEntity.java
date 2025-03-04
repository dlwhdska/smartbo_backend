package com.bo.meetingroom.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="meetingroom_detail")
@DynamicInsert
public class MeetingroomDetailEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private Integer maxNum;
    
    private Integer monitor;
    
    private Integer socket;
    
    private Integer projector;
    
    private Integer marker;
    
    private String location;
    
    // 양방향 설정
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "meetingroom")
    private List<MeetingReservationEntity> reservation; // 회의실별 예약내역
    
}
