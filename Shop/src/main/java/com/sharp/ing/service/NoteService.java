package com.sharp.ing.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.ing.domain.NoteDAO;
import com.sharp.ing.domain.NoteDTO;
import com.sharp.ing.domain.Shopping_noteDTO;
import com.sharp.ing.domain.Shopping_note_headerDTO;

@Service("NoteService")
public class NoteService {

	private Shopping_noteDTO noteDTO;
	private NoteDAO noteDAO;

	JSONObject notedata = new JSONObject();

	@Autowired
	public NoteService(Shopping_noteDTO noteDTO, NoteDAO noteDAO) {
		this.noteDTO = noteDTO;
		this.noteDAO = noteDAO;
	}

	//리스트 생성
	public void NoteHead(String userId) throws Exception {
		noteDAO.insertNoteHead(userId);
	}
	//물품등록
	public void NoteBody(List<Shopping_noteDTO> notes) throws Exception {
		int noteId = noteDAO.selectNoteId();

		for (Shopping_noteDTO note: notes) {
			note.setNote_id(noteId);
		}
		noteDAO.insertnote(notes);
	}


	// 리스트, 물품 전체조회
	public Object viewTotalNoteAll(String userId) throws Exception{
		List<NoteDTO> listTotalNoteHeader = noteDAO.viewTotalNoteHeader(userId);
		List<NoteDTO> listTotalNoteBody = noteDAO.viewTotalNoteBody(userId);

		List totalNote = new ArrayList<>();

		for(NoteDTO NoteHeader : listTotalNoteHeader) {
			Map<String, Object> note = new Hashtable<>();
			note.put("head", NoteHeader);

			List<NoteDTO> listnotes = new ArrayList<>();
			for (NoteDTO NoteBody : listTotalNoteBody) {
				if (NoteHeader.getNote_id() == NoteBody.getNote_id()) {
					listnotes.add(NoteBody);
				}
			}
			note.put("body", listnotes);
			totalNote.add(note);
		}
		return totalNote;
	}


	// 리스트, 물품 상세조회
	public JSONObject viewNoteitem(String userId, int note_id) throws Exception{
		NoteDTO listNoteHeader = noteDAO.viewNoteHead(userId, note_id);
		List<Shopping_noteDTO> listNoteBody = noteDAO.viewNoteItem(userId, note_id);
		notedata.put("head", listNoteHeader);
		notedata.put("body", listNoteBody);

		return notedata;
	}

	// 리스트 수정
	public void EditNoteHeader(String userId, int note_id) throws Exception {
		noteDAO.editNoteHeader(userId, note_id);
	}

	// 물품 수정
	public void EditNote(List<Shopping_noteDTO> editNote) throws Exception {
		noteDAO.editnote(editNote);
	}

	// 노트 삭제
	public void DeleteNote(int note_id) throws Exception {
		noteDAO.deleteNote(note_id);
	}


	// 물품 삭제
	public void DeleteNoteitem(int note_id ,int note_no) throws Exception {
		noteDAO.deleteNoteitem(note_id, note_no);
	}
}
