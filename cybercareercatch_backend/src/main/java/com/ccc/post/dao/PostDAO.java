package com.ccc.post.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.post.dto.CommentDTO;
import com.ccc.post.dto.CommentViewDTO;
import com.ccc.post.dto.CommentWriteDTO;
import com.ccc.post.dto.NoticeDTO;
import com.ccc.post.dto.PostDTO;
import com.ccc.post.dto.PostDetailDTO;
import com.ccc.post.dto.PostListDTO;
import com.ccc.post.dto.PostWriteDTO;

public class PostDAO {
	private SqlSession sqlSession;

	public PostDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// =========================================================
	// 공지
	// =========================================================

	public List<NoticeDTO> selectFreeNoticeList() {
		return sqlSession.selectList("post.selectFreeNoticeList");
	}

	public NoticeDTO selectFreeNoticeDetail(int postNumber) {
		return sqlSession.selectOne("post.selectFreeNoticeDetail", postNumber);
	}

	public void insertNotice(NoticeDTO noticeDTO) {
		sqlSession.insert("post.insertNotice", noticeDTO);
	}

	public void deleteNotice(int postNumber) {
		sqlSession.delete("post.deleteNotice", postNumber);
	}

	public NoticeDTO selectAdminFreeNotice() {
		return sqlSession.selectOne("post.selectAdminFreeNotice");
	}

	public void saveAdminFreeNotice(String noticeContent, int adminNumber) {
		Integer count = sqlSession.selectOne("post.getAdminFreeNoticeCount");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("noticeContent", noticeContent);
		params.put("adminNumber", adminNumber);

		if (count == null || count == 0) {
			sqlSession.insert("post.insertAdminFreeNotice", params);
			return;
		}

		sqlSession.update("post.updateAdminFreeNotice", params);
	}

	// =========================================================
	// 자유게시판 게시글
	// =========================================================

	public int getTotal() {
		Integer total = sqlSession.selectOne("post.getTotal");
		return total == null ? 0 : total;
	}

	public void updateReadCount(int postNumber) {
		sqlSession.update("post.updateReadCount", postNumber);
	}

	public void insert(PostWriteDTO postWriteDTO) {
		sqlSession.insert("post.insert", postWriteDTO);
	}

	public void delete(PostDTO postDTO) {
		sqlSession.delete("post.delete", postDTO);
	}

	public PostDetailDTO select(int postNumber) {
		return sqlSession.selectOne("post.select", postNumber);
	}

	public List<PostListDTO> selectAll(Map<String, Integer> pageMap) {
		return sqlSession.selectList("post.selectAll", pageMap);
	}

	// =========================================================
	// 관리자 자유게시판 관리
	// =========================================================

	public int getAdminFreePostTotal() {
		Integer total = sqlSession.selectOne("post.getAdminFreePostTotal");
		return total == null ? 0 : total;
	}

	public List<PostListDTO> selectAdminFreePostList(Map<String, Integer> pageMap) {
		return sqlSession.selectList("post.selectAdminFreePostList", pageMap);
	}

	public void deleteAdminFreePost(int postNumber) {
		sqlSession.delete("post.deleteAdminFreePost", postNumber);
	}

	// =========================================================
	// 댓글
	// =========================================================

	public List<CommentViewDTO> selectCommentList(int postNumber) {
		return sqlSession.selectList("post.selectCommentList", postNumber);
	}

	public void insertComment(CommentWriteDTO commentWriteDTO) {
		sqlSession.insert("post.insertComment", commentWriteDTO);
	}

	public void deleteComment(CommentDTO commentDTO) {
		sqlSession.delete("post.deleteComment", commentDTO);
	}
}