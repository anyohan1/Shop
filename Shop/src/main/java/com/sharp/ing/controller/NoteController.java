package com.sharp.ing.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sharp.ing.domain.NoteDTO;
import com.sharp.ing.domain.Shopping_noteDTO;
import com.sharp.ing.domain.Shopping_note_headerDTO;
import com.sharp.ing.service.NoteService;



@RestController
@CrossOrigin(origins = "*")
public class NoteController {


	private Shopping_note_headerDTO headerDTO;
	private Shopping_noteDTO noteDTO;
	private NoteService service;

	JSONObject TotalShopping = new JSONObject();
	List<JSONObject> listTotalNoteBody;
	List<NoteDTO> listNote;
	List<NoteDTO> viewTotalNoteHeader;
	List<NoteDTO> viewTotalNoteBody;
	JSONObject TotalNote = new JSONObject();

	Gson gson = new Gson();
	Gson gson2 = new Gson();

	@Autowired
	public NoteController(Shopping_note_headerDTO headerDTO, Shopping_noteDTO noteDTO, NoteService service) {
		this.headerDTO = headerDTO;
		this.noteDTO = noteDTO;
		this.service = service;
	}


	
	
	
	
	// 노트 생성, 물품등록
	@RequestMapping(value = "/note", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public @ResponseBody String note(@RequestBody List<Map<String, Object>> param, Authentication authentication) throws Exception {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername(); 
		service.NoteHead(userId);
			
		Shopping_noteDTO[] array = gson.fromJson(param.toString(), Shopping_noteDTO[].class);
		List<Shopping_noteDTO> item = Arrays.asList(array);
		service.NoteBody(item);
				
		return "success";
	}
	
	
	
	

	// 노트 전체조회
	@RequestMapping("/totalnote")
	public Object viewTotalNoteAll(Authentication authentication, Model model) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();	
		Object totalnote = service.viewTotalNoteAll(userId);
		model.addAttribute(totalnote);

		return totalnote;
	}


	// 노트 상세조회
	@RequestMapping("/viewnote")
	public JSONObject viewNoteitem(Authentication authentication, @RequestParam(value = "note_id") Integer note_id) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();

		JSONObject detail = service.viewNoteitem(userId, note_id);

		return detail;
	}


	// 노트 수정
	@RequestMapping(value = "/editnote", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public @ResponseBody String EditNote(Authentication authentication, @RequestBody JSONObject param) throws Exception {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();

		Integer noteId = gson2.fromJson(param.get("note_id").toString(), Integer.class);
		service.EditNoteHeader(userId,noteId);
		
		Shopping_noteDTO[] test =  gson2.fromJson(param.get("body").toString(), Shopping_noteDTO[].class);
		List<Shopping_noteDTO> items = Arrays.asList(test);
		for(Shopping_noteDTO a : items) {
			a.setNote_id(noteId);
		}
		service.EditNote(items);

		return "success";
	}



	// 노트 삭제
	@RequestMapping(value = "/deletenote")
	public String DeleteNote(@RequestParam(value = "note_id") int note_id)
			throws Exception {
		service.DeleteNote(note_id);
		return "success";
	}


	// 노트 물품 개별 삭제
	@RequestMapping(value = "/deletenoteitem")
	public String DeleteItem(@RequestParam(value = "note_id") int note_id, @RequestParam(value = "note_no") int note_no)
			throws Exception {
		service.DeleteNoteitem(note_id, note_no);
		return "success";
	}
}