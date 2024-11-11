package net.developia.mapper;

import java.util.List;

import net.developia.domain.BoardVO;

public interface BoardMapper {
	// @Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long dno);
	
	public int update(BoardVO board);
}
