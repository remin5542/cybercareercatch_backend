package com.ccc.expo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.expo.dto.ExpoCalendarDTO;
import com.ccc.expo.dto.ExpoDTO;
import com.ccc.expo.dto.ExpoDetailDTO;
import com.ccc.expo.dto.ExpoListDTO;

public class ExpoDAO {
	private SqlSession sqlSession;

	public ExpoDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	/* =========================
	 * 관리자 박람회 일정 목록
	 * =========================
	 */
	public List<ExpoListDTO> selectExpoList(Map<String, Integer> pageMap) {
		return sqlSession.selectList("expo.selectExpoList", pageMap);
	}

	public int getExpoTotal() {
		return sqlSession.selectOne("expo.getExpoTotal");
	}

	public void insertExpo(ExpoDTO expoDTO) {
		sqlSession.insert("expo.insertExpo", expoDTO);
	}

	public void deleteExpo(int expoNumber) {
		sqlSession.delete("expo.deleteExpo", expoNumber);
	}

	public ExpoDTO selectExpoInfo(int expoNumber) {
		return sqlSession.selectOne("expo.selectExpoInfo", expoNumber);
	}

	public List<ExpoDetailDTO> selectExpoDetailList(int expoNumber) {
		return sqlSession.selectList("expo.selectExpoDetailList", expoNumber);
	}

	public List<CompanyDTO> selectAvailableCompanyList(int expoNumber) {
		return sqlSession.selectList("expo.selectAvailableCompanyList", expoNumber);
	}

	public void insertExpoCompany(int expoNumber, int companyNumber) {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("expoNumber", expoNumber);
		paramMap.put("companyNumber", companyNumber);

		sqlSession.insert("expo.insertExpoCompany", paramMap);
	}

	/* =========================
	 * 사용자 달력 / 상세
	 * =========================
	 */
	public List<ExpoCalendarDTO> selectExpoCalendarList() {
		return sqlSession.selectList("expo.selectExpoCalendarList");
	}
}