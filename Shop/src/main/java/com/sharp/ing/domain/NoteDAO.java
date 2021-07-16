package com.sharp.ing.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("noteDAO")
public interface NoteDAO {


	// 노트 생성
	public void insertNoteHead(String userId) throws Exception;

	// 목록 생성
	public Integer selectNoteId() throws Exception;
	public void insertnote(List<Shopping_noteDTO> note) throws Exception;

	// 노트, 목록 전체조회 
	public List<NoteDTO> viewTotalNoteHeader(String userId) throws Exception;
	public List<NoteDTO> viewTotalNoteBody(String userId) throws Exception;	

	// 노트, 목록 상세조회 
	public NoteDTO viewNoteHead(String userId, int note_id) throws Exception;
	public List<Shopping_noteDTO> viewNoteItem(String userId, int note_id) throws Exception;	

	// 노트, 목록 수정
	public void editNoteHeader(String userId, int note_id) throws Exception;
	public void editnote(List<Shopping_noteDTO> editNote) throws Exception;

	// 노트 삭제
	public void deleteNote(int note_id) throws Exception;

	// 목록 삭제
	public void deleteNoteitem(int note_id ,int note_no) throws Exception;

}
