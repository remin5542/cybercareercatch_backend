package com.ccc.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.admin.dto.AdminCompanyListDTO;
import com.ccc.admin.dto.AdminDTO;
import com.ccc.admin.dto.AdminMemberListDTO;
import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.member.dto.MemberDTO;

/*
 * 관리자 관련 DAO이다.
 */
public class AdminDAO {
	public SqlSession sqlSession;

	public AdminDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	/* =========================
	 * 로그인 / 대시보드
	 * =========================
	 */

	public AdminDTO login(AdminDTO adminDTO) {
		return sqlSession.selectOne("admin.login", adminDTO);
	}

	public int getMemberTotal() {
		return sqlSession.selectOne("admin.getMemberTotal");
	}

	public int getCompanyMemberCount() {
		return sqlSession.selectOne("admin.getCompanyMemberCount");
	}

	public int getExpoCount() {
		return sqlSession.selectOne("admin.getExpoCount");
	}

	/* =========================
	 * 일반회원 정보 조회
	 * =========================
	 */

	public int getMemberListTotal(Map<String, Object> searchMap) {
		return sqlSession.selectOne("admin.getMemberListTotal", searchMap);
	}

	public List<AdminMemberListDTO> selectMemberList(Map<String, Object> pageMap) {
		return sqlSession.selectList("admin.selectMemberList", pageMap);
	}

	public AdminMemberListDTO selectMemberUserInfo(int userNumber) {
		return sqlSession.selectOne("admin.selectMemberUserInfo", userNumber);
	}

	public MemberDTO selectMemberDetail(int userNumber) {
		return sqlSession.selectOne("admin.selectMemberDetail", userNumber);
	}

	public void deleteMemberComments(int userNumber) {
		sqlSession.delete("admin.deleteMemberComments", userNumber);
	}

	public void deleteMemberPosts(int userNumber) {
		sqlSession.delete("admin.deleteMemberPosts", userNumber);
	}

	public void deleteMember(int userNumber) {
		sqlSession.delete("admin.deleteMember", userNumber);
	}

	/* =========================
	 * 기업회원 승인 / 반려
	 * =========================
	 */

	public List<AdminCompanyListDTO> selectCompanyApproveList(Map<String, Integer> pageMap) {
		return sqlSession.selectList("admin.selectCompanyApproveList", pageMap);
	}

	public int getCompanyApproveTotal() {
		return sqlSession.selectOne("admin.getCompanyApproveTotal");
	}

	public CompanyDetailDTO selectCompanyApproveDetail(int companyNumber) {
		return sqlSession.selectOne("admin.selectCompanyApproveDetail", companyNumber);
	}

	public void updateCompanyState(CompanyDTO companyDTO) {
		sqlSession.update("admin.updateCompanyState", companyDTO);
	}

	/* =========================
	 * 기업회원 정보 조회
	 * =========================
	 */

	public int getRecruiterListTotal(Map<String, Object> searchMap) {
		return sqlSession.selectOne("admin.getRecruiterListTotal", searchMap);
	}

	public List<AdminMemberListDTO> selectRecruiterList(Map<String, Object> pageMap) {
		return sqlSession.selectList("admin.selectRecruiterList", pageMap);
	}

	public AdminMemberListDTO selectRecruiterUserInfo(int userNumber) {
		return sqlSession.selectOne("admin.selectRecruiterUserInfo", userNumber);
	}

	public void deleteRecruiterComments(int userNumber) {
		sqlSession.delete("admin.deleteRecruiterComments", userNumber);
	}

	public void deleteRecruiter(int userNumber) {
		sqlSession.delete("admin.deleteRecruiter", userNumber);
	}
}