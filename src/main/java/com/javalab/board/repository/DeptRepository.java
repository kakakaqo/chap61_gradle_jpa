package com.javalab.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.board.entity.Dept;

/*
 * 레바지토리 인터페이스로 다음 코드로 작성만 해놓으면 내부적으로 
 * C/R/U/D가 자동으로 구현되어진다.
 * 
 * JpaRepository<Dept, String> :
 * - Dept : 엔티티의 타입(클래스)이름, 
 * 	 String : 키 컬럼의 자료형(wrapper) 타입
 */
public interface DeptRepository extends JpaRepository<Dept, String>{

}
