package com.hx.dmcp.mysql.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hx.dmcp.mysql.entity.Keyword;
import com.hx.dmcp.mysql.service.KeywordService;
import com.hx.dmcp.util.Pagination;

/**
 * @author krisjin
 */
@Controller
@RequestMapping("/admin/ms")
public class KeywordController {
	
	@Autowired
	private KeywordService keywordService;

	@RequestMapping(value = "/keyword.htm", method = RequestMethod.GET)
	public String listNews(@RequestParam(value = "p", defaultValue = "1") int p, ModelMap model) {
		Pagination<Keyword> page = keywordService.getKeywordWithPage(p, 15);
		model.put("page", page);
		return "page/keyword/listKeyword";
	}

	@RequestMapping(value = "/keyword/{id}.htm", method = RequestMethod.GET)
	public String keywordDetail(@PathVariable("id") int id, ModelMap model) {
		Keyword keyword = keywordService.getKeywordById(id);
		model.put("keyword", keyword);
		return "page/keyword/keywordDetail";
	}
	
	@RequestMapping(value = "/keyword/update.html", method = RequestMethod.GET)
	public String updateKeyword(@RequestParam(value = "id", defaultValue = "0") int id, ModelMap model){
		Keyword keyword =keywordService.getKeywordById(id);
		model.put("keyword", keyword);
		return "page/keyword/keywordDetail";
	}
	
}
