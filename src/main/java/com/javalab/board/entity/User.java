package com.javalab.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.boot.ansi.AnsiColors.BitDepth;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 엔티티(Entity) 클래스
 * 
 * @Entity : JPA가 해당 클래스를 관리하게 됨, 데이터베이스의 테이블과 직접 관련되는 객체임.
 * @Table : 엔티티와 매핑할 테이블을 지정(엔티티 클래스의 이름과 실제 매핑될 테이블의 이름이 다를 경우에 주로 사용한다.)
 * 
 * @Id
 *   : 테이블의 기본키에 사용할 속성을 지정함. 고유 번호 id 속성에 적용한 
 *   @Id 애너테이션은 id 속성을 기본 키로 지정한다. 기본 키로 지정하면
 *   이제 id 속성의 값은 데이터베이스에 저장할 때 동일한 값으로 저장할 수 없다.
 *   고유 번호를 기본 키로 한 이유는 고유 번호는 엔티티에서 각 데이터를 구분하는 유효한 값으로 중복되면 안 되기 때문이다.
 *
 * @GeneratedValue
 *  : 기본키 생성 방법 설정. 데이터를 저장할 때 해당 속성에 값을 따로
 *  세팅하지 않아도 1씩 자동으로 증가하여 저장된다. strategy는
 *  고유번호를 생성하는 옵션으로 GenerationType.IDENTITY는 해당
 *  컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용한다.
 *  
 * @Column
 *  : 엔티티의 속성은 테이블의 컬럼명과 일치하는데 컬럼의 세부 설정을 위해
 *  @Column 애너테이션을 사용한다. length는 컬럼의 길이를 설정할때
 *  사용하고 columnDefinition은 컬럼의 송성을 정의할 때 사용한다.
 *  (- @Column(name = "age", columnDefinition = "INT(3) UNSIGNED")
 *  private Integer age; 이라고 하면 age 컬럼명은 INT Type이고 최대 3자리이고 양수만 올수 있다.)
 *  엔티티의 속성은 @Column 애너테이션을 사용하지 않더라도 테이블 컬럼으로 인식한다.
 *  테이블 컬럼으로 인식하고 싶지 않은 경우에만 @Transient 애너테이션을 사용한다.
 *  
 *  createDate 속성의 실제 테이블의 컬러명은 create_date가 된다.
 *  즉 createDate처럼 대소문자 형태의 카멜케이스(Camel Case) 이름은
 *  create_date 처럼 모두 소문자로 변경되고 언더바(_)로 단어가 구분되어 실제 테이블 컬럼명이 된다.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 20, nullable = false)
	private String name;
	
	@Column(length = 3)
	private Integer age;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createDate;
	
	@ManyToOne // JPA 연관 다수 대 하나 
	@JoinColumn(name = "deptid")
	private Dept dept;
	
	// 영속화 되기 전에 그 시점의 날짜를 세팅
	@PrePersist
	public void prePersist() {
		createDate = LocalDateTime.now();
	}
}
