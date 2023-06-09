package com.javalab.board.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_dept")
public class Dept {

	@Id
	@Column(length = 20, nullable = false)
	private String deptId;
	
	@Column(length = 50, nullable = false)
	private String deptName;

	/*
	 * cascade = CascadeType.REMOVE 조건 제외
	 * - 부서가 삭제되면 해당 부서에 소속된 User Entity 함께 삭제(위험)
	 * mappedBy : 나는 User라는 엔티티를 참조하긴 하는데 그 엔티티와
	 * 	외래키로 맺어진 사이는 아니다. 단순히 참조만 할뿐이다.
	 * dept : User Entity에 Dept dept 멤버 변수 이름이다.
	 */
	@OneToMany(mappedBy = "dept")
	private List<User> userList;
}
